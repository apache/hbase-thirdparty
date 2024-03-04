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
HBASE-15789_V2.patch
HBASE-17087.patch
HBASE-17239.patch
```

Ideally we would be pushing this set up into protobuf project.

Note that this project requires JDK8. This is because a bunch of the code we
have in hbase-unsafe is using old APIs that have been removed from more
modern JDKs. Due to a bug in JDK, we cannot generate this code using a more
modern version of the JDK. See
[HBASE-26773](https://issues.apache.org/jira/browse/HBASE-26773) for details.

## Build/Deploy

To build, make sure that your environment uses JDK8, then just run:

```sh
mvn clean package
```

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
