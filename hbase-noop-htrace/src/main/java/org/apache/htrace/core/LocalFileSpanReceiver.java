/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.htrace.core;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Writes the spans it receives to a local file.
 */
public class LocalFileSpanReceiver extends SpanReceiver {
  public static final String PATH_KEY = "local.file.span.receiver.path";
  public static final String CAPACITY_KEY = "local.file.span.receiver.capacity";
  public static final int CAPACITY_DEFAULT = 5000;

  public LocalFileSpanReceiver(HTraceConfiguration conf) {
  }

  @Override
  public void receiveSpan(Span span) {
  }

  @Override
  public void close() throws IOException {
  }

  public static String getUniqueLocalTraceFileName() {
    String tmp = System.getProperty("java.io.tmpdir", "/tmp");
    String nonce = UUID.randomUUID().toString();
    return new File(tmp, nonce).getAbsolutePath();
  }
}
