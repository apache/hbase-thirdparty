#!/usr/bin/env bash
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

# Script to generate toolchains.xml with configurable Java paths

# Set default paths if environment variables are not set
if [ -z "$JAVA8_HOME" ]; then
    echo "Trying to detect Java 8 installation (Jenkins/CI standard path)"
    if [ -d "/usr/lib/jvm/java-8" ]; then
        JAVA8_HOME="/usr/lib/jvm/java-8"
    else
        echo "Warning: JAVA8_HOME not set and Java 8 not found at /usr/lib/jvm/java-8"
        echo "For local development, please set JAVA8_HOME environment variable"
        JAVA8_HOME="/usr/lib/jvm/java-8"
    fi
fi

if [ -z "$JAVA17_HOME" ]; then
    echo "Trying to detect Java 17 installation (Jenkins/CI standard path)"
    if [ -d "/usr/lib/jvm/java-17" ]; then
        JAVA17_HOME="/usr/lib/jvm/java-17"
    else
        echo "Warning: JAVA17_HOME not set and Java 17 not found at /usr/lib/jvm/java-17"
        echo "For local development, please set JAVA17_HOME environment variable"
        JAVA17_HOME="/usr/lib/jvm/java-17"
    fi
fi

echo "Generating toolchains.xml with:"
echo "  JAVA8_HOME: $JAVA8_HOME"
echo "  JAVA17_HOME: $JAVA17_HOME"

# Generate toolchains.xml
cat > toolchains.xml << EOF
<?xml version="1.0" encoding="UTF-8"?>
<toolchains xmlns="http://maven.apache.org/TOOLCHAINS/1.1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/TOOLCHAINS/1.1.0 http://maven.apache.org/xsd/toolchains-1.1.0.xsd">
  <!--
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

-->
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>1.8</version>
    </provides>
    <configuration>
      <jdkHome>$JAVA8_HOME</jdkHome>
    </configuration>
  </toolchain>
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>17</version>
    </provides>
    <configuration>
      <jdkHome>$JAVA17_HOME</jdkHome>
    </configuration>
  </toolchain>
</toolchains>
EOF

echo "toolchains.xml generated successfully!"
