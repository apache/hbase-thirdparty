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
# HBASE  hbase-thirdparty-3.1.0 Release Notes

These release notes cover new developer and user-facing incompatibilities, important issues, features, and major improvements.


---

* [HBASE-23088](https://issues.apache.org/jira/browse/HBASE-23088) | *Major* | **[hbase-thirdparty] Upgrade the dependencies for hbase-thirdparty**

    netty-all: 4.1.34.Final =\> 4.1.42.Final
    guava: 27.1-jre =\> 28.1-jre
    commons-collections4: 4.3 =\> 4.4
    protobuf: 3.7.1 =\> 3.9.2



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



