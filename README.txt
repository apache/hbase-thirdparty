This project packages relocated third-party libraries used by Apache HBase.

DISCLAIMER: This is an HBase internal project.  Included libs and/or their
versions change at the dictate of hbase without regard to others concerns.

To build, just run:

 $ mvn install

To deploy to mvn central, run:

 $ mvn install deploy  -Papache-release -Prelease 

(Make sure you have your credentials set over in ~/.m2/settings.xml, etc. -- see
hbase refguide for publishing SNAPSHOTS/Release Candidates)

We have two submodules, one to patch and then relocate (shade) protobuf. The other
modules relocate a bundle of other (unpatched) libs used by hbase. This latter
set includes protobuf-util, netty-all, gson, and guava.

All shading is done using the same relocation offset of
org.apache.hadoop.hbase.shaded; we add this prefix to the relocated thirdparty
library class names.

See the pom.xml for the explicit version of each third-party lib included.

Note that in hbase-shaded-protobuf, we unzip the protobuf jar to src/main/java
rather than to a dir under target because the jar plugin wants src here (its
hard to convince it otherwise).
