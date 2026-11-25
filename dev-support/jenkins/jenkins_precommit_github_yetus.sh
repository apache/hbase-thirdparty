#!/usr/bin/env bash
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

set -e

# place ourselves in the directory containing the hbase and yetus checkouts
cd "$(dirname "$0")/../.."
echo "executing from $(pwd)"

if [[ "true" = "${DEBUG}" ]]; then
  set -x
  printenv 2>&1 | sort
fi

declare -i missing_env=0
declare -a required_envs=(
  # these ENV variables define the required API with the Jenkinsfile and the
  # GitHub Actions workflows under .github/workflows/
  "ARCHIVE_PATTERN_LIST"
  "DOCKERFILE"
  "GITHUB_PASSWORD"
  "GITHUB_USER"
  "PATCHDIR"
  "PLUGINS"
  "SET_JAVA_HOME"
  "SOURCEDIR"
  "YETUSDIR"
)
# Validate params
for required_env in "${required_envs[@]}"; do
  if [ -z "${!required_env}" ]; then
    echo "[ERROR] Required environment variable '${required_env}' is not set."
    missing_env=${missing_env}+1
  fi
done

# BUILD_URL_ARTIFACTS is required for Jenkins but set by Yetus itself for GitHub Actions
if [[ "${GITHUB_ACTIONS}" != "true" ]] && [[ -z "${BUILD_URL_ARTIFACTS}" ]]; then
  echo "[ERROR] Required environment variable 'BUILD_URL_ARTIFACTS' is not set."
  missing_env=${missing_env}+1
fi

if [ ${missing_env} -gt 0 ]; then
  echo "[ERROR] Please set the required environment variables before invoking. If this error is " \
       "on Jenkins, then please file a JIRA about the error."
  exit 1
fi

# TODO (HBASE-23900): cannot assume test-patch runs directly from sources
TESTPATCHBIN="${YETUSDIR}/bin/test-patch"

# this must be clean for every run
rm -rf "${PATCHDIR}"
mkdir -p "${PATCHDIR}"

# Gather machine information
mkdir "${PATCHDIR}/machine"
"${SOURCEDIR}/dev-support/jenkins/gather_machine_environment.sh" "${PATCHDIR}/machine"

# If CHANGE_URL is set (e.g., Github Branch Source plugin), process it.
# Otherwise exit, because we don't want HBase to do a
# full build.  We wouldn't normally do this check for smaller
# projects. :)
# On GitHub Actions Yetus determines the pull request by itself from the
# GitHub Actions environment (see yetus' robots.d/githubactions.sh), so only
# apply this check outside of GitHub Actions.
if [[ "${GITHUB_ACTIONS}" != "true" ]] && [[ -z "${CHANGE_URL}" ]]; then
  echo "Full build skipped" > "${PATCHDIR}/report.html"
  exit 0
fi
# enable debug output for yetus
if [[ "true" = "${DEBUG}" ]]; then
  YETUS_ARGS+=("--debug")
fi
# If we're doing docker, make sure we don't accidentally pollute the image with a host java path
if [ -n "${JAVA_HOME}" ]; then
  unset JAVA_HOME
fi
YETUS_ARGS+=("--patch-dir=${PATCHDIR}")
# where the source is located
YETUS_ARGS+=("--basedir=${SOURCEDIR}")
# lots of different output formats
YETUS_ARGS+=("--console-report-file=${PATCHDIR}/console.txt")
# enable writing back to Github
YETUS_ARGS+=("--github-token=${GITHUB_PASSWORD}")
# GitHub Actions cannot write comments on pull requests coming from forks
# (the automatically provided GITHUB_TOKEN is read-only there), so rely on the
# job summary and the uploaded artifacts instead. Jenkins keeps writing
# comments via its own credentials.
if [[ "${GITHUB_ACTIONS}" != "true" ]]; then
  YETUS_ARGS+=("--github-write-comment")
fi
# auto-kill any surefire stragglers during unit test runs
YETUS_ARGS+=("--reapermode=kill")
# set relatively high limits for ASF machines
# changing these to higher values may cause problems
# with other jobs on systemd-enabled machines
YETUS_ARGS+=("--dockermemlimit=20g")
# rsync these files back into the archive dir
YETUS_ARGS+=("--archive-list=${ARCHIVE_PATTERN_LIST}")
# URL for user-side presentation in reports and such to our artifacts
if [[ -n "${BUILD_URL_ARTIFACTS}" ]]; then
  YETUS_ARGS+=("--build-url-artifacts=${BUILD_URL_ARTIFACTS}")
fi
# plugins to enable
YETUS_ARGS+=("--plugins=${PLUGINS}")
YETUS_ARGS+=("--tests-filter=test4tests")
# run in docker mode and specifically point to our
# Dockerfile since we don't want to use the auto-pulled version.
YETUS_ARGS+=("--docker")
YETUS_ARGS+=("--dockerfile=${DOCKERFILE}")
YETUS_ARGS+=("--mvn-custom-repos")
# GitHub Actions runners are single-use, so a fixed instance name is safe and
# keeps yetus' maven repo directory stable across runs
# (~/yetus-m2/<project>-<branch>-patch-ci instead of ...-patch-<random>), which
# is what makes the ~/yetus-m2 cache effective. Keep the default random
# instance on shared Jenkins nodes so concurrent builds don't clobber each
# other's repository.
if [[ "${GITHUB_ACTIONS}" = "true" ]]; then
  YETUS_ARGS+=("--instance=ci")
fi
YETUS_ARGS+=("--java-home=${SET_JAVA_HOME}")
# effectively treat dev-support as a custom maven module
YETUS_ARGS+=("--skip-dirs=dev-support")
# help keep the ASF boxes clean
YETUS_ARGS+=("--sentinel")
# use emoji vote so it is easier to find the broken line
YETUS_ARGS+=("--github-use-emoji-vote")
YETUS_ARGS+=("--github-repo=apache/hbase-thirdparty")

echo "Launching yetus with command line:"
echo "${TESTPATCHBIN} ${YETUS_ARGS[*]}"

/usr/bin/env bash "${TESTPATCHBIN}" "${YETUS_ARGS[@]}"
