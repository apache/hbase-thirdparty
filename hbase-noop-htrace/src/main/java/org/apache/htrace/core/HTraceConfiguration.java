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

import java.util.Map;

/**
 * Wrapper which integrating applications should implement in order
 * to provide tracing configuration.
 */
public abstract class HTraceConfiguration {

  /**
   * An empty HTrace configuration.
   */
  public static final HTraceConfiguration EMPTY = new HTraceConfiguration() {
    @Override
    public String get(String key) {
      return null;
    }
    @Override
    public String get(String key, String defaultValue) {
      return defaultValue;
    }
  };

  /**
   * Create an HTrace configuration from a map.
   *
   * @param conf    The map to create the configuration from.
   * @return        The new configuration.
   */
  public static HTraceConfiguration fromMap(Map<String, String> conf) {
    return EMPTY;
  }

  public static HTraceConfiguration fromKeyValuePairs(String... pairs) {
    return EMPTY;
  }

  public abstract String get(String key);

  public abstract String get(String key, String defaultValue);

  public boolean getBoolean(String key, boolean defaultValue) {
    return defaultValue;
  }

  public int getInt(String key, int defaultVal) {
    return defaultVal;
  }

}
