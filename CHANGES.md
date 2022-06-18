# HBASE Changelog
<!---
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.



CHANGES.md and RELEASENOTES.md were generated using yetus releasedocmaker.

First make sure what is in JIRA agrees with what is in git and vice-versa.
Thirdparty version numbers are of the form thirdparty-x.y.z.

Then run the hbase script that updates CHANGES and RELEASENOTES. For
example:

 $  source ../hbase.git/dev-support/create-release/release-util.sh; update_releasenotes `pwd` thirdparty-2.2.0

The 'pwd' argument says where the thirdparty files to edit are and the
last argument is the version to search JIRA with.

DO NOT REMOVE THIS MARKER; FOR INTERPOLATING CHANGES!-->
# HBASE Changelog

## Release thirdparty-4.1.1 - Unreleased (as of 2022-06-18)



### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-27137](https://issues.apache.org/jira/browse/HBASE-27137) | [hbase-thirdparty] Set version as 4.1.1 in master in prep for first RC of 4.1.1 |  Major | build, pom, thirdparty |
| [HBASE-27138](https://issues.apache.org/jira/browse/HBASE-27138) | [hbase-thirdparty] Generate CHANGES.md and RELEASENOTES.md for 4.1.1 |  Major | thirdparty |


### OTHER:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-27135](https://issues.apache.org/jira/browse/HBASE-27135) | [hbase-thirdparty] Bump checkstyle from 8.28 to 8.29 in /hbase-noop-htrace |  Major | security, thirdparty |
| [HBASE-27134](https://issues.apache.org/jira/browse/HBASE-27134) | [hbase-thirdparty] Bump junit from 4.12 to 4.13.1 in /hbase-noop-htrace |  Major | security, thirdparty |
| [HBASE-27130](https://issues.apache.org/jira/browse/HBASE-27130) | [hbase-thirdparty] Bump dependency versions |  Major | thirdparty |
| [HBASE-27133](https://issues.apache.org/jira/browse/HBASE-27133) | [hbase-thirdparty] Bump checkstyle from 8.28 to 8.29 in /hbase-unsafe |  Major | security, thirdparty |
| [HBASE-26893](https://issues.apache.org/jira/browse/HBASE-26893) | [hbase-thirdparty] Upgrade jackson to 2.13.3 |  Major | thirdparty |


## Release thirdparty-4.1.0 - Unreleased (as of 2022-03-04)



### IMPROVEMENTS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-26773](https://issues.apache.org/jira/browse/HBASE-26773) | [hbase-thirdparty] Introduce a hbase-unsafe module in hbase-thirdparty to remove the direct references of Unsafe in our main code base |  Major | compatibility, thirdparty |


### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-26746](https://issues.apache.org/jira/browse/HBASE-26746) | Update protobuf-java to 3.19.4 |  Minor | Protobufs, thirdparty |
| [HBASE-26733](https://issues.apache.org/jira/browse/HBASE-26733) | [hbase-thirdparty] Upgrade Netty to 4.1.73.Final |  Major | security, thirdparty |
| [HBASE-26732](https://issues.apache.org/jira/browse/HBASE-26732) | [hbase-thirdparty] Update jackson (databind) to 2.13.1 |  Major | security, thirdparty |


### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-26795](https://issues.apache.org/jira/browse/HBASE-26795) | [hbase-thirdparty] Generate CHANGES.md and RELEASENOTES.md for 4.1.0 |  Major | thirdparty |
| [HBASE-26794](https://issues.apache.org/jira/browse/HBASE-26794) | [hbase-thirdparty] Set version as 4.1.0 in master in prep for first RC of 4.1.0 |  Major | build, pom, thirdparty |


### OTHER:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-26781](https://issues.apache.org/jira/browse/HBASE-26781) | [hbase-thirdparty] Introduce the sun.misc.Signal delegation in hbase-unsafe |  Major | thirdparty |


## Release thirdparty-4.0.1 - Unreleased (as of 2021-12-17)



### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-26593](https://issues.apache.org/jira/browse/HBASE-26593) | [hbase-thirdparty] Set version as 4.0.1 in prep for first RC |  Major | build, pom, thirdparty |
| [HBASE-26594](https://issues.apache.org/jira/browse/HBASE-26594) | [hbase-thirdparty] Generate CHANGES.md and RELEASENOTES.md for 4.0.1 |  Major | thirdparty |
| [HBASE-26592](https://issues.apache.org/jira/browse/HBASE-26592) | Fix the broken shaded protobuf module |  Blocker | hbase-thirdparty, Protobufs |


## Release thirdparty-4.0.0 - Unreleased (as of 2021-11-30)



### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-26496](https://issues.apache.org/jira/browse/HBASE-26496) | [hbase-thirdparty] Exclude the original protobuf-java jar when shading |  Major | hbase-thirdparty |


### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-26515](https://issues.apache.org/jira/browse/HBASE-26515) | [hbase-thirdparty] Generate CHANGES.md and RELEASENOTES.md for 4.0.0 |  Major | thirdparty |
| [HBASE-26514](https://issues.apache.org/jira/browse/HBASE-26514) | [hbase-thirdparty] Set version as 4.0.0 in prep for first RC |  Major | build, pom, thirdparty |
| [HBASE-25868](https://issues.apache.org/jira/browse/HBASE-25868) | [hbase-thirdparty] Shade jackson-jaxrs-json-provider for use with shaded jersey |  Major | hbase-thirdparty |
| [HBASE-25863](https://issues.apache.org/jira/browse/HBASE-25863) | [hbase-thirdparty] Shade javax.ws.rs package for use with shaded Jersey |  Major | thirdparty |
| [HBASE-26506](https://issues.apache.org/jira/browse/HBASE-26506) | [hbase-thirdparty] Bump jersey version to 2.35 |  Major | hbase-thirdparty |
| [HBASE-26505](https://issues.apache.org/jira/browse/HBASE-26505) | [hbase-thirdparty] Bump commons-cli version to 1.5.0 |  Major | hbase-thirdparty |
| [HBASE-26504](https://issues.apache.org/jira/browse/HBASE-26504) | [hbase-thirdparty] Bump maven plugin versions |  Major | build, hbase-thirdparty, pom |
| [HBASE-26503](https://issues.apache.org/jira/browse/HBASE-26503) | [hbase-thirdparty] Bump guava version to 31.0.1-jre |  Major | hbase-thirdparty |
| [HBASE-26502](https://issues.apache.org/jira/browse/HBASE-26502) | [hbase-thirdparty] Bump gson version to 2.8.9 |  Major | hbase-thirdparty |
| [HBASE-26501](https://issues.apache.org/jira/browse/HBASE-26501) | [hbase-thirdparty] Bump jetty version to 9.4.44.v20210927 |  Major | hbase-thirdparty |
| [HBASE-26499](https://issues.apache.org/jira/browse/HBASE-26499) | [hbase-thirdparty] Bump netty version to 4.1.70.Final |  Major | hbase-thirdparty |
| [HBASE-26509](https://issues.apache.org/jira/browse/HBASE-26509) | [hbase-thirdparty] Bump version to 4.0.0-SNAPSHOT on master branch |  Major | build, hbase-thirdparty, pom |
| [HBASE-26500](https://issues.apache.org/jira/browse/HBASE-26500) | [hbase-thirdparty] Bump protobuf version to 3.19.1 |  Major | hbase-thirdparty |


## Release thirdparty-3.5.1 - Unreleased (as of 2021-06-02)



### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25959](https://issues.apache.org/jira/browse/HBASE-25959) | Relocate libnetty\_transport\_native\_epoll\_aarch\_64.so in hbase-thirdparty |  Minor | hbase-thirdparty |
| [HBASE-25746](https://issues.apache.org/jira/browse/HBASE-25746) | [hbase-thirdparty] Update jetty to \>= 9.4.39 due to CVE-2021-28165 |  Major | thirdparty |
| [HBASE-25728](https://issues.apache.org/jira/browse/HBASE-25728) | [hbase-thirdparty] Upgrade Netty library to \>= 4.1.60 due to security vulnerability CVE-2021-21295 |  Major | thirdparty |


### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25946](https://issues.apache.org/jira/browse/HBASE-25946) | [hbase-thirdparty] Generate CHANGES.md and RELEASENOTES.md for 3.5.1 |  Major | thirdparty |
| [HBASE-25945](https://issues.apache.org/jira/browse/HBASE-25945) | [hbase-thirdparty] Set version as 3.5.1 in prep for first RC |  Major | thirdparty |


### OTHER:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25962](https://issues.apache.org/jira/browse/HBASE-25962) | [hbase-thirdparty] Relocate mac jnilib for netty |  Major | thirdparty |
| [HBASE-25943](https://issues.apache.org/jira/browse/HBASE-25943) | [hbase-thirdparty] Bump dependencis in hbase-thirdparty |  Major | dependencies, thirdparty |
| [HBASE-25855](https://issues.apache.org/jira/browse/HBASE-25855) | [hbase-thirdparty] Fix typo in jersey relocation path |  Minor | hbase-thirdparty |


## Release hbase-thirdparty-3.5.0 - Unreleased (as of 2021-02-09)



### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25557](https://issues.apache.org/jira/browse/HBASE-25557) | hbase thirdparty jdk bytecode enforcement shouldn't check test dependencies |  Major | hbase-thirdparty |
| [HBASE-25552](https://issues.apache.org/jira/browse/HBASE-25552) | [hbase-thirdparty] Update jetty version to fix CVE-2020-27218 |  Major | dependencies, hbase-thirdparty |
| [HBASE-24802](https://issues.apache.org/jira/browse/HBASE-24802) | make a drop-in compatible impl of htrace APIs that does not do anything |  Critical | Client, dependencies, thirdparty |



## Release thirdparty-3.4.1 - Unreleased (as of 2020-11-13)



### IMPROVEMENTS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25262](https://issues.apache.org/jira/browse/HBASE-25262) | [hbase-thirdparty] Update jetty version in hbase-thirdparty |  Major | dependencies, hbase-thirdparty |


### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25319](https://issues.apache.org/jira/browse/HBASE-25319) | [hbase-thirdparty] Add JerseyFlowSubscriber to ignoreClass in maven enforcer plugin |  Blocker | build, hbase-thirdparty |


### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25270](https://issues.apache.org/jira/browse/HBASE-25270) | [hbase-thirdparty] Generate CHANGES.md and RELEASENOTES.md for 3.4.1 |  Major | thirdparty |
| [HBASE-25269](https://issues.apache.org/jira/browse/HBASE-25269) | [hbase-thirdparty] Set version as 3.4.1 in prep for first RC |  Major | thirdparty |


### OTHER:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-25265](https://issues.apache.org/jira/browse/HBASE-25265) | [hbase-thirdparty] Bump dependencis in hbase-thirdparty |  Major | dependencies, hbase-thirdparty |
| [HBASE-25264](https://issues.apache.org/jira/browse/HBASE-25264) | [hbase-thirdparty] Update jersey version in hbase-thirdparty |  Major | dependencies, hbase-thirdparty |


## Release thirdparty-3.4.0 - Unreleased (as of 2020-08-17)



### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-24858](https://issues.apache.org/jira/browse/HBASE-24858) | [hbase-thirdparty] Builds without a \`clean\` phase fail on hbase-shaded-jetty |  Minor | thirdparty |
| [HBASE-24798](https://issues.apache.org/jira/browse/HBASE-24798) | [hbase-thirdparty] Ignore module-info and SubmissionPublisher in enforceBytecodeVersion rule |  Major | build, thirdparty |


### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-24785](https://issues.apache.org/jira/browse/HBASE-24785) | [hbase-thirdparty] Generate CHANGES.md and RELEASENOTES.md for 3.4.0 |  Major | thirdparty |
| [HBASE-24784](https://issues.apache.org/jira/browse/HBASE-24784) | [hbase-thirdparty] Set version as 3.4.0 in prep for first RC |  Major | thirdparty |


### OTHER:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-24860](https://issues.apache.org/jira/browse/HBASE-24860) | [hbase-thirdparty] Bump copyright year in NOTICE |  Trivial | thirdparty |
| [HBASE-24820](https://issues.apache.org/jira/browse/HBASE-24820) | [hbase-thirdparty] Add jersey-hk2 when shading jersey and bump jetty to 9.4.31 |  Major | dependencies, hbase-thirdparty |
| [HBASE-24801](https://issues.apache.org/jira/browse/HBASE-24801) | [hbase-thirdparty] Remove assembly plugin for generating src tarballs |  Major | thirdparty |
| [HBASE-24782](https://issues.apache.org/jira/browse/HBASE-24782) | [hbase-thirdparty] Bump dependencis in hbase-thirdparty |  Major | dependencies, thirdparty |
| [HBASE-19256](https://issues.apache.org/jira/browse/HBASE-19256) | [hbase-thirdparty] shade jetty |  Major | dependencies, thirdparty |


## Release thirdparty-3.3.0 - Unreleased (as of 2020-04-28)



### IMPROVEMENTS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-24149](https://issues.apache.org/jira/browse/HBASE-24149) | Bump netty version to 4.1.48.Final |  Major | thirdparty |
| [HBASE-23790](https://issues.apache.org/jira/browse/HBASE-23790) | Bump netty version to 4.1.45.Final in hbase-thirdparty |  Major | hbase-thirdparty |


### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-24279](https://issues.apache.org/jira/browse/HBASE-24279) | Fix thirdparty-3.2.0 release notes |  Trivial | thirdparty |


### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-23750](https://issues.apache.org/jira/browse/HBASE-23750) | [hbase-thirdparty] Add nexus-staging-maven-plugin to apache-release profile |  Major | . |


### OTHER:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-24259](https://issues.apache.org/jira/browse/HBASE-24259) | [thirdparty] Update Protobuf to 3.11.4 |  Major | thirdparty |
| [HBASE-24216](https://issues.apache.org/jira/browse/HBASE-24216) | Add .idea directory to Gitignore in hbase-thirdparty |  Trivial | thirdparty |
| [HBASE-24241](https://issues.apache.org/jira/browse/HBASE-24241) | [hbase-thirdparty] Bump the dependencies for hbase-thirdparty |  Major | hbase-thirdparty |
| [HBASE-24214](https://issues.apache.org/jira/browse/HBASE-24214) | Update Apache POM to version 23 for hbase-thirdparty |  Minor | thirdparty |


## Release hbase-thirdparty-3.2.0 - Unreleased (as of 2020-01-22)



### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-23718](https://issues.apache.org/jira/browse/HBASE-23718) | [hbase-thirdparty] Update libs; pb from 3.9 to 3.11, etc. |  Major | . |
| [HBASE-23717](https://issues.apache.org/jira/browse/HBASE-23717) | [hbase-thirdparty] Change pom version from 3.1.2-SNAPSHOT to 3.2.0-SNAPSHOT |  Major | thirdparty |


## Release hbase-thirdparty-3.1.1 - Unreleased (as of 2019-10-08)



### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-23132](https://issues.apache.org/jira/browse/HBASE-23132) | Squash hbase-thirdparty-3.1.0; was compiled w/ jdk10 so "NoSuchMethodError: java.nio.ByteBuffer.\*" |  Major | . |


## Release hbase-thirdparty-3.1.0 - Unreleased (as of 2019-10-04)



### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-23088](https://issues.apache.org/jira/browse/HBASE-23088) | [hbase-thirdparty] Upgrade the dependencies for hbase-thirdparty |  Major | hbase-thirdparty |


## Release thirdparty-3.0.0 - Unreleased (as of 2019-09-22)

### IMPROVEMENTS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-23052](https://issues.apache.org/jira/browse/HBASE-23052) | hbase-thirdparty version of GSON that works for branch-1 |  Blocker | dependencies |

