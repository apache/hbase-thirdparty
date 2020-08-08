# RELEASENOTES

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
#
# Be careful doing manual edits in this file. Do not change format
# of release header or remove the below marker. This file is generated.
# DO NOT REMOVE THIS MARKER; FOR INTERPOLATING CHANGES!-->
# HBASE  thirdparty-3.4.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-24820](https://issues.apache.org/jira/browse/HBASE-24820) | *Major* | **[hbase-thirdparty] Add jersey-hk2 when shading jersey and bump jetty to 9.4.31**

Jetty 9.4.30 -\> 9.4.31
Shade jersey-hk2 in hbase-shaded-jersey.


---

* [HBASE-24798](https://issues.apache.org/jira/browse/HBASE-24798) | *Major* | **[hbase-thirdparty] Ignore module-info and SubmissionPublisher in enforceBytecodeVersion rule**

Ignore module-info in enforceBytecodeVersion rule as it is for supporting for JDK9+ and will not break JDK8 support.
Ignore SubmissionPublisher in enforceBytecodeVersion rule as it is used by jersey-common for supporting JDK11 and will not break JDK8 support.
Jersey 2.x promises to support JDK8.


---

* [HBASE-24801](https://issues.apache.org/jira/browse/HBASE-24801) | *Major* | **[hbase-thirdparty] Remove assembly plugin for generating src tarballs**

Remove the maven-assembly-plugin for generating src tarballs. We will generate it with 'git archive' command in release scripts.


---

* [HBASE-24782](https://issues.apache.org/jira/browse/HBASE-24782) | *Major* | **[hbase-thirdparty] Bump dependencis in hbase-thirdparty**

netty 4.1.18.Final \> 4.1.51.Final
protobuf 3.11.4 -\> 3.12.2


---

* [HBASE-19256](https://issues.apache.org/jira/browse/HBASE-19256) | *Major* | **[hbase-thirdparty] shade jetty**

Introduce two new modules for hbase-thirdparty, one is hbase-shaded-jetty, for shading jetty related jars, and the other is hbase-shaded-jersey, for shading jersey related jars.



# HBASE  thirdparty-3.3.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-24279](https://issues.apache.org/jira/browse/HBASE-24279) | *Trivial* | **Fix thirdparty-3.2.0 release notes**

The hbase-thirdparty 3.2.0 release incorrectly listed gson upgrade. The gson version in 3.2.0 and 3.3.0 stayed on 2.8.5.


---

* [HBASE-24259](https://issues.apache.org/jira/browse/HBASE-24259) | *Major* | **[thirdparty] Update Protobuf to 3.11.4**

The version of Protobuf provided is now 3.11.4.


---

* [HBASE-24241](https://issues.apache.org/jira/browse/HBASE-24241) | *Major* | **[hbase-thirdparty] Bump the dependencies for hbase-thirdparty**

guava 28.2-jre =\> 29.0-jre


---

* [HBASE-24149](https://issues.apache.org/jira/browse/HBASE-24149) | *Major* | **Bump netty version to 4.1.48.Final**

The version of Netty provided is now 4.1.48.Final. This is expected to be a compatible change from version 4.1.45.Final provided in the previous release.



# HBASE  hbase-thirdparty-3.2.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-23718](https://issues.apache.org/jira/browse/HBASE-23718) | *Major* | **[hbase-thirdparty] Update libs; pb from 3.9 to 3.11, etc.**

guava: 28.1-jre =\> 28.2-jre
error\_prone: 2.3.3 =\> 2.3.4
netty: 4.1.42.Final =\> 4.1.44.Final
protobuf: 3.9.2 =\> 3.11.1
maven-assembly-plugin: 3.1.1 =\> 3.2.0



# HBASE  hbase-thirdparty-3.1.1 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.



# HBASE  hbase-thirdparty-3.1.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-23088](https://issues.apache.org/jira/browse/HBASE-23088) | *Major* | **[hbase-thirdparty] Upgrade the dependencies for hbase-thirdparty**

    netty-all: 4.1.34.Final =\> 4.1.42.Final
    guava: 27.1-jre =\> 28.1-jre
    commons-collections4: 4.3 =\> 4.4
    protobuf: 3.7.1 =\> 3.9.2



# HBASE  hbase-thirdparty-3.0.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-23052](https://issues.apache.org/jira/browse/HBASE-23052) | *Blocker* | **hbase-thirdparty version of GSON that works for branch-1**

<!-- markdown -->
HBase's internal use of GSON is now done in a stand alone module named `hbase-shaded-gson` rather than as a part of the `hbase-shaded-miscellaneous` module. The relocated fully qualified class names are still the same.

This internal artifact is also set to maintain JDK bytecode compatibility as appropriate for use with branches-1 based releases in addition to the existing use in later release lines.



