# HBase Thirdparty
<!---
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
-->

This project packages relocated third-party libraries used by Apache HBase.

> DISCLAIMER: This project is for Apache HBase internal use.  Included libs
> and/or their versions are subject to change at the dictate of hbase without
> regard to the concern of others!

We have a number of submodules, one per ornery lib -- protobuf, netty, &c. --
where we need special-handling and then a bucket for all the rest,
hbase-shaded-miscellaneous. This latter includes protobuf-util, gson, and guava.

General philosophy is many modules rather than a few fat ones so we can keep
dependency narrow; a fat jar would put a load of unnecessaries on the
CLASSPATH. The hbase-shaded-miscellaneous is a sort of all-the-rest but it
is also libs that depend on each other and are awkward to disentangle.

All shading is done using the same relocation offset of
org.apache.hbase.thirdparty. We add this prefix to the relocated thirdparty
library class names.

See the pom.xml for the explicit version of each third-party lib included.

Note that in hbase-shaded-protobuf, we unzip the protobuf jar to src/main/java
rather than to a dir under target because the jar plugin wants src here (its
hard to convince it otherwise). We also apply some patches. Current set are:

```
HBASE-15789_V4.patch
HBASE-17087.patch
HBASE-17239.patch
```

Ideally we would be pushing this set up into protobuf project.

### Steps to generate patches for protobuf-java:
In case build fails due to protobuf-java version change, we can follow below steps to generate new patch files.

1) Clone the protobuf git repo.
    ```sh
    git clone https://github.com/protocolbuffers/protobuf
    ```
2) Checkout to the appropriate release tag.
    ```sh
    cd protobuf
    git checkout v29.2
    ```
   NOTE: Ensure to update above tag based on your target protobuf-java version. Also, protobuf repo has a special way of versioning so that different languages can have different major versions. For example, here v29.2 maps to 4.29.2 for protobuf-java.
3) Create a branch for applying the patches.
    ```sh
    git checkout -b apply_patches
    ```
4) Apply patches from hbase-thirdparty's 'hbase-shaded-protobuf/src/main/patches' one by one, in-order. For example:
    ```sh
    git apply --directory java/core <BASE_DIR_TO_HBASE_THIRDPARTY_CODE>/hbase-thirdparty/hbase-shaded-protobuf/src/main/patches/HBASE-15789_V3.patch
    ```
   NOTE: Ensure to replace <BASE_DIR_TO_HBASE_THIRDPARTY_CODE> based on your setup.
5) Resolve any conflicts. Next, stage all changes and commit the change.
6) Generate a patch from previous commit and save the patch file, if there are code changes/conflict.
    ```sh
    git diff HEAD^ HEAD > HBASE-15789.patch
    ```
7) Trim the prefix directory 'java/core' from the generated patch.
    ```sh
    sed -i '' 's|java/core/src/main/java/|src/main/java/|g' HBASE-15789.patch
    ```
8) Repeat steps 4 to 7 for each patch.
9) Copy and overwrite old patches at 'hbase-shaded-protobuf/src/main/patches' with the updated patches, in case there was any code changes/conflict.

---

## Dual JDK Requirements

Starting with version 4.1.12, this project requires both JDK 8 and JDK 17 to accommodate different HBase versions:

- **HBase 2.x**: Uses JDK 8 compatible modules (including `hbase-shaded-jetty` with Jetty 9)
- **HBase 3.x**: Uses JDK 17 modules (including `hbase-shaded-jetty-12-plus-*` with Jetty 12)

Jetty 12 requires JDK 17 for compilation, but HBase 2.x deployments cannot move to Jetty 12 for JDK 8 compatibility. Our solution provides a single release containing modules for both JDK versions, eliminating the need for separate branches or releases.

### JDK 8 Required Modules

This project has specific JDK requirements for different modules:

- **hbase-unsafe**: Must be built with JDK 8 because it uses old APIs that have been removed from more modern JDKs. Due to a bug in JDK, we cannot generate this code using a more modern version of the JDK. See [HBASE-26773](https://issues.apache.org/jira/browse/HBASE-26773) for details.
- **hbase-shaded-protobuf**: Must be built with JDK 8 because it depends on internal Java APIs such as `sun.misc.Unsafe`. These internal APIs are inaccessible when compiling with newer JDKs but release target set to JDK 8.

### JDK 17 Compilation with JDK 8 Target

**All other modules** (including Jetty 12 modules) use JDK 17 for compilation but with release target set to JDK 8.

### Maven Enforcer Plugin Profiles

The project uses Maven enforcer plugin profiles to ensure these requirements are met:
- **enforce-jdk8-bytecode**: Ensures hbase-unsafe and hbase-shaded-protobuf are built with JDK 8.
- **enforce-jdk17-bytecode**: For **Jetty 12 modules** (hbase-shaded-jetty-12-plus-*), the enforcer plugin allows JDK 17 bytecode to be included from Jetty 12 dependencies, while still using JDK 8 as the release target.

### Why toolchains are required?

Maven needs explicit toolchain configuration to automatically select JDK 8 for hbase-unsafe and hbase-shaded-protobuf modules, and JDK 17 for all other modules including Jetty 12 modules. Environment variables alone are insufficient.

### Files
- `generate-toolchains.sh` - Script to generate toolchains.xml with configurable paths
- `toolchains.xml` - Generated Maven toolchains configuration file (not checked in)

## Build/Deploy

### Local Development Setup

1. **Install both JDK versions**: JDK 8 and JDK 17
2. **Set environment variables**:
   ```sh
   export JAVA8_HOME=/path/to/your/jdk8
   export JAVA17_HOME=/path/to/your/jdk17
   ```
3. **Choose your toolchain setup approach** (see options below)

### Toolchain Setup Options

**Option 1: Project-local toolchains.xml**
```sh
# Generate and use project-specific toolchains
export JAVA8_HOME=/path/to/your/jdk8
export JAVA17_HOME=/path/to/your/jdk17
./dev-support/generate-toolchains.sh

mvn clean install -t toolchains.xml
```

**Option 2: Global Maven toolchains setup**
```sh
# Setup toolchains in ~/.m2/ directory
export JAVA8_HOME=/path/to/your/jdk8
export JAVA17_HOME=/path/to/your/jdk17
./dev-support/generate-toolchains.sh
cp toolchains.xml ~/.m2/toolchains.xml

mvn clean install
```

### CI/Jenkins Setup

The Jenkins CI environment uses a Docker-based build system that automatically handles the dual JDK requirements without any manual configuration.

#### Multi-JDK Docker Environment

The Jenkins build uses a custom Dockerfile (`dev-support/jenkins/Dockerfile`) that:
- Downloads and installs both JDK 8 (Adoptium Temurin) and JDK 17 (Adoptium Temurin)
- Places them at standardized paths:
  - Java 8: `/usr/lib/jvm/java-8`
  - Java 17: `/usr/lib/jvm/java-17`
- Includes Maven 3.9.8 for the build process

#### Maven Wrapper with Automatic Toolchains

The Docker image creates a Maven wrapper that automatically handles toolchain configuration by replacing the original `mvn` command with a wrapper that always passes the `-t ${BASEDIR}/dev-support/toolchains-jenkins.xml` parameter to ensure the correct toolchains file is used for every Maven invocation.

#### Automatic JDK Selection

The system uses a pre-configured toolchains file (`dev-support/toolchains-jenkins.xml`) that:
- Defines JDK 1.8 toolchain pointing to `/usr/lib/jvm/java-8`
- Defines JDK 17 toolchain pointing to `/usr/lib/jvm/java-17`
- Allows Maven to automatically select the correct JDK for each module based on the toolchain requirements in their POMs

#### Jenkins Build Process

In Jenkins, the build process is completely automated:
1. Docker container starts with both JDKs pre-installed
2. Maven wrapper automatically passes the toolchains configuration
3. Each module uses the appropriate JDK version:
   - `hbase-unsafe` and `hbase-shaded-protobuf`: Built with JDK 8
   - All other modules: Built with JDK 17 (with JDK 8 release target)
4. No manual toolchain setup or environment configuration required

#### Environment Variables

The Jenkinsfile sets the following environment variables:
```bash
SET_JAVA_HOME="/usr/lib/jvm/java-17"    # Default JDK for the build
JAVA8_HOME="/usr/lib/jvm/java-8"        # JDK 8 location
JAVA17_HOME="/usr/lib/jvm/java-17"      # JDK 17 location
```

This automated setup ensures consistent builds across all Jenkins jobs without requiring developers or maintainers to manually configure toolchains in the CI environment.


## Release

To cut a release candidate, update JIRA. The hbase-thirdparty currently uses
hbase JIRA but with versions specified with a 'thirdparty-x.y.z'.

Use the `dev-support/create-release` scripts found in `hbase.git:master`. This
requires having the `gpg-agent` running and configured correctly. Specify the
project name and git repo path. See `do-release-docker.sh -h` for details. For
example:

```sh
% mkdir ~/tmp/hbase-thirdparty-4.1.6RC0/
% ~/src/hbase/dev-support/create-release/do-release-docker.sh \
    -d ~/tmp/hbase-thirdparty-4.1.6RC0 \
    -p hbase-thirdparty
```

Try the new artifact by having hbase use the staged jar. Do this in your hbase pom:

```
diff --git a/pom.xml b/pom.xml
index 112f95a892..dab9e7a6bd 100755
--- a/pom.xml
+++ b/pom.xml
@@ -1451,7 +1451,7 @@
     <spotbugs.version>3.1.0-RC3</spotbugs.version>
     <wagon.ssh.version>2.12</wagon.ssh.version>
     <xml.maven.version>1.0.1</xml.maven.version>
-    <hbase-thirdparty.version>2.1.0</hbase-thirdparty.version>
+    <hbase-thirdparty.version>2.2.0</hbase-thirdparty.version>
     <!-- Intraproject jar naming properties -->
     <!-- TODO this is pretty ugly, but works for the moment.
       Modules are pretty heavy-weight things, so doing this work isn't too bad. -->
@@ -3774,4 +3774,11 @@
       <url>file:///tmp</url>
     </site>
   </distributionManagement>
+  <repositories>
+    <repository>
+      <id>staging</id>
+      <name>staging</name>
+      <url>https://repository.apache.org/content/repositories/orgapachehbase-1296</url>
+    </repository>
+  </repositories>
 </project>
```

Send out an email with details on staging repo and pointers to the uploaded
artifacts.
