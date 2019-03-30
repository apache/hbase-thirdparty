This project packages relocated third-party libraries used by Apache HBase.

DISCLAIMER: This project is for Apache HBase internal use.  Included libs
and/or their versions are subject to change at the dictate of hbase without
regard to the concern of others!

We have a number of submodules, one per ornery lib -- protobuf and netty --
where we need special-handling and then a bucket for all the rest,
hbase-shaded-miscellaneous. This latter includes protobuf-util, gson, and guava.

General philosophy is many modules rather than a few fat ones so we can keep
dependency narrow; a fat jar would put a load of unnecessaries on the
CLASSPATH. The hbase-shaded-miscellaneous is a sort of all-the-rest but it
is also libs that depend on each other and are awkward to disentangle.

All shading is done using the same relocation offset of
org.apache.hbase.thirdparty. We add this prefix to the relocated thirdparty
library class names.

See the pom.xml for the explicit version of each third-party lib included.

Note that in hbase-shaded-protobuf, we unzip the protobuf jar to src/main/java
rather than to a dir under target because the jar plugin wants src here (its
hard to convince it otherwise). We also apply some patches. Current set are:

  HBASE-15789_V2.patch
  HBASE-17087.patch
  HBASE-17239.patch

Ideally we would be pushing this set up into protobuf project.

To build, just run:

 $ mvn install

To deploy to mvn central, run:

 $ mvn install deploy  -Papache-release -Prelease

(Make sure you have your credentials set over in ~/.m2/settings.xml, etc. -- see
hbase refguide for publishing SNAPSHOTS/Release Candidates)

To build a src tarball:

 $ mvn clean assembly:single

To cut a release candidate, update JIRA. The hbase-thirdparty
currently uses hbase JIRA but with versions specified with a
''thirdparty-' prefix as in thirdparty-2.2.0.

To generate the source tarball do as follows (below uses 2.2.0
as target version and 2.2.0RC0 as the tag to use):

 $ git archive --format=tar.gz --output=../hbase-thirdparty-2.2.0-src.tar.gz --prefix="hbase-thirdparty-2.2.0/" 2.2.0RC0

Sign and get the hash of the tgz:

 $ gpg --print-md  SHA512 hbase-thirdparty-2.2.0-src.tar.gz > hbase-thirdparty-2.2.0-src.tar.gz.sha512
 $ gpg  --armour --output hbase-thirdparty-2.2.0-src.tar.gz.asc --detach-sig hbase-thirdparty-2.2.0-src.tar.gz

Check in the content under apache/dist in hbase/thirdparty:

 $ cp -r dir_of_artifacts ~/checkouts/apache.dist/dev/hbase/hbase-thirdparty/hbase-thirdparty-2.2.0RC0

Add the dir and commit it.

Now upload to the maven staging repository:

 $ mvn install deploy  -Papache-release -Prelease

Login to repository.apache.org and 'close' the uploaded repo.

Try the new artifact by having hbase use the staged jar. Do this in your hbase pom:

diff --git a/pom.xml b/pom.xml
index 112f95a892..dab9e7a6bd 100755
--- a/pom.xml
+++ b/pom.xml
@@ -1451,7 +1451,7 @@
     <spotbugs.version>3.1.0-RC3</spotbugs.version>
     <wagon.ssh.version>2.12</wagon.ssh.version>
     <xml.maven.version>1.0.1</xml.maven.version>
-    <hbase-thirdparty.version>2.1.0</hbase-thirdparty.version>
+    <hbase-thirdparty.version>2.2.0</hbase-thirdparty.version>
     <!-- Intraproject jar naming properties -->
     <!-- TODO this is pretty ugly, but works for the moment.
       Modules are pretty heavy-weight things, so doing this work isn't too bad. -->
@@ -3774,4 +3774,11 @@
       <url>file:///tmp</url>
     </site>
   </distributionManagement>
+  <repositories>
+    <repository>
+      <id>staging</id>
+      <name>staging</name>
+      <url>https://repository.apache.org/content/repositories/orgapachehbase-1296</url>
+    </repository>
+  </repositories>
 </project>

Send out an email with details on staging repo and pointers to the uploaded
artifacts.




