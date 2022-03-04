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
# HBASE  thirdparty-4.1.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-26773](https://issues.apache.org/jira/browse/HBASE-26773) | *Major* | **[hbase-thirdparty] Introduce a hbase-unsafe module in hbase-thirdparty to remove the direct references of Unsafe in our main code base**

Introduced a new hbase-unsafe module in hbase-thirdparty.


---

* [HBASE-26733](https://issues.apache.org/jira/browse/HBASE-26733) | *Major* | **[hbase-thirdparty] Upgrade Netty to 4.1.73.Final**

Upgrade netty to 4.1.73.Final for addressing CVE-2021-43797.


---

* [HBASE-26732](https://issues.apache.org/jira/browse/HBASE-26732) | *Major* | **[hbase-thirdparty] Update jackson (databind) to 2.13.1**

Upgrade jackson databind from 2.10.1 -\> 2.13.1.

For addressing a possible Dos attack described in https://github.com/FasterXML/jackson-databind/issues/3328.



# HBASE  thirdparty-4.0.1 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-26592](https://issues.apache.org/jira/browse/HBASE-26592) | *Blocker* | **Fix the broken shaded protobuf module**

Remove protobuf-java dependency from hbase-shaded-protobuf module, as we will unpack its source and patch it and then recompile from source. Also add a resources section to make sure that we include the proto files in the final jar.



# HBASE  thirdparty-4.0.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-26506](https://issues.apache.org/jira/browse/HBASE-26506) | *Major* | **[hbase-thirdparty] Bump jersey version to 2.35**

Upgrade jersey version

2.32 -\> 2.35

Add jersey-media-jaxb dependency.


---

* [HBASE-26505](https://issues.apache.org/jira/browse/HBASE-26505) | *Major* | **[hbase-thirdparty] Bump commons-cli version to 1.5.0**

Upgrade commons-cli version

1.4 -\> 1.5.0


---

* [HBASE-26504](https://issues.apache.org/jira/browse/HBASE-26504) | *Major* | **[hbase-thirdparty] Bump maven plugin versions**

Upgrade versions of some maven plugins:

maven-dependency-plugin: 3.1.1 -\> 3.2.0
build-helper-maven-plugin: 3.0.1-SNAPSHOT -\>3.2.0
maven-compiler-plugi:  -\>3.8.1
maven-source-plugin: -\> 3.2.0
maven-enforcer-plugin: 1.4.1 -\> 3.0.0
extra-enforcer-rules: 1.3 -\> 1.4


---

* [HBASE-26503](https://issues.apache.org/jira/browse/HBASE-26503) | *Major* | **[hbase-thirdparty] Bump guava version to 31.0.1-jre**

Upgrade guava version

30.1.1-jre -\> 31.0.1-jre


---

* [HBASE-26502](https://issues.apache.org/jira/browse/HBASE-26502) | *Major* | **[hbase-thirdparty] Bump gson version to 2.8.9**

Upgrade gson version

2.8.5 -\>2.8.9


---

* [HBASE-26501](https://issues.apache.org/jira/browse/HBASE-26501) | *Major* | **[hbase-thirdparty] Bump jetty version to 9.4.44.v20210927**

Upgrade jetty version

9.4.41.v20210516 -\> 9.4.44.v20210927


---

* [HBASE-26499](https://issues.apache.org/jira/browse/HBASE-26499) | *Major* | **[hbase-thirdparty] Bump netty version to 4.1.70.Final**

Upgrade netty version

4.1.65.Final -\> 4.1.70.Final


---

* [HBASE-26509](https://issues.apache.org/jira/browse/HBASE-26509) | *Major* | **[hbase-thirdparty] Bump version to 4.0.0-SNAPSHOT on master branch**

Bump version to 4.0.0-SNAPSHOT, drop JDK7 support.


---

* [HBASE-26500](https://issues.apache.org/jira/browse/HBASE-26500) | *Major* | **[hbase-thirdparty] Bump protobuf version to 3.19.1**

Upgrade protobuf version

3.17.1 -\> 3.19.1



# HBASE  thirdparty-3.5.1 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-25943](https://issues.apache.org/jira/browse/HBASE-25943) | *Major* | **[hbase-thirdparty] Bump dependencis in hbase-thirdparty**

protobuf 3.13.0 =\> 3.17.1
netty 4.1.63.Final =\> 4.1.65.Final
guava 30.0-jre =\> 30.1.1-jre
error\_prone\_annotations 2.3.4 =\> 2.7.1
jetty 9.4.39.v20210325 =\> 9.4.41.v20210516


---

* [HBASE-25855](https://issues.apache.org/jira/browse/HBASE-25855) | *Minor* | **[hbase-thirdparty] Fix typo in jersey relocation path**

We have a typo in our jersey relocation path, where we use 'jersery' instead of 'jersey'.

This is a minor change, but indeed an incompatible change. But since hbase-thirdparty is only used by hbase, it is not a big problem for end users. We will update the thridparty dependency for all active branches.


---

* [HBASE-25728](https://issues.apache.org/jira/browse/HBASE-25728) | *Major* | **[hbase-thirdparty] Upgrade Netty library to \>= 4.1.60 due to security vulnerability CVE-2021-21295**

Netty is upgrade to 4.1.63.Final due to security vulnerability CVE-2021-21295.



# HBASE  hbase-thirdparty-3.5.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-24802](https://issues.apache.org/jira/browse/HBASE-24802) | *Critical* | **make a drop-in compatible impl of htrace APIs that does not do anything**

<!-- markdown -->

The HBase Thirdparty project now produces an artifact that is a drop in replacement for the defunct HTrace project's library. This artifact is binary and source compatible with the artifact produced by the HTrace incubating 4.2.0 release with the exception of APIs that expressly referred to the relocated version of Jackson it included.


To use this library you should remove the `htrace-core4`  jar file from your runtime classpath and add the `hbase-noop-htrace` jar in its place.

For use in maven, add an exclusion to any dependencies that have a transitive dependency on HTrace:

```
<exclusions>
  <exclusion>
    <groupId>org.apache.htrace</groupId>
    <artifactId>htrace-core4</artifactId>
  </exclusion>
</exclusions>
```

Once the HTrace library has been excluded you should add a dependency on the replacement implementation at an appropriate scope. e.g. for the first release that includes this artifact at the default (compile) scope:

```
<dependency>
  <groupId>org.apache.hbase.thirdparty</groupId>
  <artifactId>hbase-noop-htrace</artifactId>
  <version>3.5.0</version>
</dependency>
```

For a detailed compatibility report between the libraries please see the attachments on jira for HBASE-24802.



# HBASE  thirdparty-3.4.1 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-25265](https://issues.apache.org/jira/browse/HBASE-25265) | *Major* | **[hbase-thirdparty] Bump dependencis in hbase-thirdparty**

netty 4.1.51.Final -\> 4.1.53.Final
protobuf 3.12.2 -\> 3.13.0
guava 29.0-jre -\> 30.0-jre


---

* [HBASE-25264](https://issues.apache.org/jira/browse/HBASE-25264) | *Major* | **[hbase-thirdparty] Update jersey version in hbase-thirdparty**

Update jersey version 2.31 -\> 2.32



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



