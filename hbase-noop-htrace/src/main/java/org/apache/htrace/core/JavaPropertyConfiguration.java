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
 * This is an implementation of HTraceConfiguration which draws its properties
 * from global Java Properties.
 */
public final class JavaPropertyConfiguration extends HTraceConfiguration {

  public static class Builder {

    public Builder() {
    }

    public Builder clearPrefixes() {
      return this;
    }

    public Builder addPrefix(String prefix) {
      return this;
    }

    JavaPropertyConfiguration build() {
      return Holder.SINGLETON;
    }
  }

  private JavaPropertyConfiguration() {
  }

  @Override
  public String get(String key) {
    return null;
  }

  @Override
  public String get(String key, String defaultValue) {
    return defaultValue;
  }

  private static final class Holder {
    public static final JavaPropertyConfiguration SINGLETON = new JavaPropertyConfiguration();
  }
}
