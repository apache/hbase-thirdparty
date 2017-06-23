This project contains relocated third-party libraries used by Apache HBase

Includes protobuf-util, netty-all, gson, and guava offset so they have
an org.apache.hadoop.hbase.shaded prefix on class name.  See the pom.xml
for the explicit version of each third-party lib included.

We do not include protobuf here because we need to patch it. Patching
involves unpack of src, patch, and then re-jarring. We can do this
if only protobuf is involved. Unpack of protobuf plus the above and
then trying to compile fails because src is missing for some dependency.
See hbase-protocol-shaded/pom.xml for how it does patch of protobuf.
