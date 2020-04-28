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

