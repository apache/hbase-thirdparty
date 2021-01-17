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
 * Sampler that returns true a certain percentage of the time. Specify the frequency interval by
 * configuring a {@code double} value for {@link #SAMPLER_FRACTION_CONF_KEY}.
 */
public class ProbabilitySampler extends Sampler {
  @SuppressWarnings("VisibilityModifier")
  public final double threshold = 0.0d;
  public final static String SAMPLER_FRACTION_CONF_KEY = "sampler.fraction";

  public ProbabilitySampler(HTraceConfiguration conf) {
  }

  @Override
  public boolean next() {
    return false;
  }
}
