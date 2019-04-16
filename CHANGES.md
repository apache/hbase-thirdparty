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
## Release thirdparty-2.2.1 - Unreleased (as of 2019-04-16)

### SUB-TASKS:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-22233](https://issues.apache.org/jira/browse/HBASE-22233) | [hbase-thirdparty] Add error prone annotations dependency explicitly and do not relocate it |  Major | thirdparty |

## Release thirdparty-2.2.0 - Unreleased (as of 2019-03-30)

### BUG FIXES:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-22137](https://issues.apache.org/jira/browse/HBASE-22137) | [hbase-thirdparty] RC0 doesn't include google.protobuf so fails PB generation if you try to use it. |  Major | . |
| [HBASE-18709](https://issues.apache.org/jira/browse/HBASE-18709) | [hbase-thirdparty] Clean out bundled proto files |  Major | hbase-thirdparty |


### OTHER:

| JIRA | Summary | Priority | Component |
|:---- |:---- | :--- |:---- |
| [HBASE-22130](https://issues.apache.org/jira/browse/HBASE-22130) | [hbase-thirdparty] Upgrade thirdparty dependencies |  Major | thirdparty |


