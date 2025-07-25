# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Dockerfile for hbase-thirdparty pre-commit build.
#
#
# Built in multiple stages so as to avoid re-downloading large binaries when
# tweaking unrelated aspects of the image.
FROM ubuntu:22.04 AS base_image
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

RUN DEBIAN_FRONTEND=noninteractive apt-get -qq update && \
  DEBIAN_FRONTEND=noninteractive apt-get -qq install --no-install-recommends -y \
    ca-certificates=20211016 \
    curl='7.81.0-*' \
    locales='2.35-*' \
    bash='5.1-*' \
    binutils='2.38-*' \
    build-essential=12.9ubuntu3 \
    git='1:2.34.1-*' \
    rsync='3.2.3-*' \
    tar='1.34+dfsg-*' \
    wget='1.21.2-*' \
    shellcheck='0.8.0-*' \
    patch='2.7.6-*' \
    && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* \
    && \
    locale-gen en_US.UTF-8
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8

##
# download sundry dependencies
#

FROM base_image AS maven_download_image
ENV MAVEN_VERSION='3.9.8'
ENV MAVEN_URL="https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz"
ENV MAVEN_SHA512='7d171def9b85846bf757a2cec94b7529371068a0670df14682447224e57983528e97a6d1b850327e4ca02b139abaab7fcb93c4315119e6f0ffb3f0cbc0d0b9a2'
SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN curl --location --fail --silent --show-error --output /tmp/maven.tar.gz "${MAVEN_URL}" && \
  echo "${MAVEN_SHA512} */tmp/maven.tar.gz" | sha512sum -c -

FROM base_image AS openjdk8_download_image
ENV OPENJDK8_URL='https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u412-b08/OpenJDK8U-jdk_x64_linux_hotspot_8u412b08.tar.gz'
ENV OPENJDK8_SHA256='b9884a96f78543276a6399c3eb8c2fd8a80e6b432ea50e87d3d12d495d1d2808'
SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN curl --location --fail --silent --show-error --output /tmp/adoptopenjdk8.tar.gz "${OPENJDK8_URL}" && \
  echo "${OPENJDK8_SHA256} */tmp/adoptopenjdk8.tar.gz" | sha256sum -c -

FROM base_image AS openjdk17_download_image
ENV OPENJDK17_URL='https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.11%2B9/OpenJDK17U-jdk_x64_linux_hotspot_17.0.11_9.tar.gz'
ENV OPENJDK17_SHA256='aa7fb6bb342319d227a838af5c363bfa1b4a670c209372f9e6585bd79da6220c'
SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN curl --location --fail --silent --show-error --output /tmp/adoptopenjdk17.tar.gz "${OPENJDK17_URL}" && \
  echo "${OPENJDK17_SHA256} */tmp/adoptopenjdk17.tar.gz" | sha256sum -c -

##
# build the final image
#

FROM base_image
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

# hadolint ignore=DL3010
COPY --from=maven_download_image /tmp/maven.tar.gz /tmp/maven.tar.gz
RUN tar xzf /tmp/maven.tar.gz -C /opt && \
  ln -s "/opt/$(dirname "$(tar -tf /tmp/maven.tar.gz | head -n1)")" /opt/maven && \
  rm /tmp/maven.tar.gz

##
# ensure JVMs are available under `/usr/lib/jvm` and prefix each installation
# as `java-` so as to conform with Yetus's assumptions.
#

# hadolint ignore=DL3010
COPY --from=openjdk8_download_image /tmp/adoptopenjdk8.tar.gz /tmp/adoptopenjdk8.tar.gz
RUN mkdir -p /usr/lib/jvm && \
  tar xzf /tmp/adoptopenjdk8.tar.gz -C /usr/lib/jvm && \
  ln -s "/usr/lib/jvm/$(basename "$(tar -tf /tmp/adoptopenjdk8.tar.gz | head -n1)")" /usr/lib/jvm/java-8-adoptopenjdk && \
  ln -s /usr/lib/jvm/java-8-adoptopenjdk /usr/lib/jvm/java-8 && \
  rm /tmp/adoptopenjdk8.tar.gz

# hadolint ignore=DL3010
COPY --from=openjdk17_download_image /tmp/adoptopenjdk17.tar.gz /tmp/adoptopenjdk17.tar.gz
RUN mkdir -p /usr/lib/jvm && \
  tar xzf /tmp/adoptopenjdk17.tar.gz -C /usr/lib/jvm && \
  ln -s "/usr/lib/jvm/$(basename "$(tar -tf /tmp/adoptopenjdk17.tar.gz | head -n1)")" /usr/lib/jvm/java-17-adoptopenjdk && \
  ln -s /usr/lib/jvm/java-17-adoptopenjdk /usr/lib/jvm/java-17 && \
  rm /tmp/adoptopenjdk17.tar.gz

# configure default environment for Yetus
ENV MAVEN_HOME='/opt/maven'

# create a wrapper for mvn to always pass the toolchains.xml file
RUN mv /opt/maven/bin/mvn /opt/maven/bin/mvn-original && \
    cat > /opt/maven/bin/mvn <<'EOF'
#!/bin/bash
TOOLCHAIN="${BASEDIR}/dev-support/toolchains-jenkins.xml"
if [ -f "$TOOLCHAIN" ]; then
  echo "Added: -t ${TOOLCHAIN} to mvn flags!"
  exec ${MAVEN_HOME}/bin/mvn-original "$@" -t "$TOOLCHAIN"
else
  echo "Unexpected: ${TOOLCHAIN} file is missing!"
  exec ${MAVEN_HOME}/bin/mvn-original "$@"
fi
EOF
RUN chmod +x /opt/maven/bin/mvn && cat /opt/maven/bin/mvn

CMD ["/bin/bash"]

###
# Everything past this point is either not needed for testing or breaks Yetus.
# So tell Yetus not to read the rest of the file:
# YETUS CUT HERE
###
