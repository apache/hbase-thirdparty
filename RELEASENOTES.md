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



CHANGES.md and RELEASENOTES.md were generated using yetus releasedocmaker.

First make sure what is in JIRA agrees with what is in git and vice-versa.
Thirdparty version numbers are of the form thirdparty-x.y.z.

Then run the hbase script that updates CHANGES and RELEASENOTES. E.g.

 $  source ../hbase.git/dev-support/create-release/release-util.sh; update_releasenotes . thirdparty-2.2.0

The 'pwd' argument says where the thirdparty files to edit are and the
last argument is the version to search JIRA with.

DO NOT REMOVE THIS MARKER; FOR INTERPOLATING RNS!-->
# HBASE  thirdparty-2.2.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-22130](https://issues.apache.org/jira/browse/HBASE-22130) | *Major* | **[hbase-thirdparty] Upgrade thirdparty dependencies**

Made following upgrades:

gson 2.8.1 -\> 2.8.5
guava 22.0 -\> 27.1-jre
pb 3.5.1 -\> 3.7.0
netty 4.1.17 -\> 4.1.34
commons-collections4 4.1 -\> 4.3

Updated apache parent pom from 18 to 21 and misc plugins.



