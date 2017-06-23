This project contains relocated third-party libraries used by Apache HBase.

We have two submodules, one to patch and then relocate (shade) protobuf. The other
modules relocates a bundle of other (unpatched) libs used by hbase. This latter
set includes protobuf-util, netty-all, gson, and guava.

All shading is done using the same relocation offset of
org.apache.hadoop.hbase.shaded; we add this prefix to the relocated thirdparty
library class names.

See the pom.xml for the explicit version of each third-party lib included.
