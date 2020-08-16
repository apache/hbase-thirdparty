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

/**
 * <p>The HTrace tracer ID.</p>
 *
 * <p>HTrace tracer IDs are created from format strings.
 * Format strings contain variables which the TracerId class will
 * replace with the correct values at runtime.</p>
 *
 * <ul>
 * <li>%{tname}: the tracer name supplied when creating the Tracer.</li>
 * <li>%{pname}: the process name obtained from the JVM.</li>
 * <li>%{ip}: will be replaced with an ip address.</li>
 * <li>%{pid}: the numerical process ID from the operating system.</li>
 * </ul>
 *
 * <p>For example, the string "%{pname}/%{ip}" will be replaced with something
 * like: DataNode/192.168.0.1, assuming that the process' name is DataNode
 * and its IP address is 192.168.0.1.</p>
 *
 *  ID strings can contain backslashes as escapes.
 * For example, "\a" will map to "a".  "\%{ip}" will map to the literal
 * string "%{ip}", not the IP address.  A backslash itself can be escaped by a
 * preceding backslash.
 */
public final class TracerId {

  /**
   * The configuration key to use for process id
   */
  public static final String TRACER_ID_KEY = "tracer.id";

  public TracerId(HTraceConfiguration conf, String tracerName) {
  }

  public String get() {
    return TRACER_ID_KEY;
  }
}
