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

