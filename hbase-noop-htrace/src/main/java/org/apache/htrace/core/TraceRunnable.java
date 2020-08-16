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
 * Wrap a Runnable with a Span that survives a change in threads.
 */
public class TraceRunnable implements Runnable {
  private final Runnable runnable;

  /**
   * @param tracer The Tracer to use for tracing.
   * @param parent The TraceScope to read parent span ID from.
   * @param runnable The Runnable that will be executed.
   * @param description An optional description to set on the trace when executing.
   * @deprecated Use {@link #TraceRunnable(Tracer, SpanId, Runnable, String)} instead.
   */
  @Deprecated
  public TraceRunnable(Tracer tracer, TraceScope parent,
      Runnable runnable, String description) {
    this.runnable = runnable;
  }

  public TraceRunnable(Tracer tracer, SpanId parentId,
      Runnable runnable, String description) {
    this.runnable = runnable;
  }

  @Override
  public void run() {
    runnable.run();
  }

  public Runnable getRunnable() {
    return runnable;
  }
}
