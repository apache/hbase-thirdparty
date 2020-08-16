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

# Call it like ./swap_htrace_jar.sh /some/place/to/target /a/path/to/component.tar.gz /a/path/to/hbase-noop-htrace.jar
set -e
if [ -n "${DEBUG}" ]; then
  set -x
fi
declare destination="$1"
declare tarball="$2"
declare noop_htrace="$3"
echo "unpack the tarball, but skip htrace artifacts."
tar -xzf "${tarball}" --strip-components=1 --exclude 'htrace*.jar' -C "${destination}"
echo "insert a copy of our replacement artifact for htrace."
for htrace_location in $(tar -tzf "${tarball}" | grep -E 'htrace.*jar' ); do
  htrace_location="$(dirname "${htrace_location#*/}")"
  echo "        placing htrace jar at ${htrace_location}"
  cp "${noop_htrace}" "${destination}/${htrace_location}"
done;
