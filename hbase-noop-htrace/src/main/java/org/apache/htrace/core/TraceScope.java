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

import java.io.Closeable;

/**
 * Create a new TraceScope at major transitions. Hosts current tracing context.
 */
@SuppressWarnings("FinalClass")
public class TraceScope implements Closeable {

  private TraceScope() {
  }

  /**
   * Returns the span which this scope is managing.
   *
   * @return The span.
   */
  public Span getSpan() {
    return null;
  }

  /**
   * Returns the span ID which this scope is managing.
   *
   * @return The span ID.
   */
  public SpanId getSpanId() {
    return SpanId.INVALID;
  }

  /**
   * Detach this TraceScope from the current thread.
   *
   * It is OK to "leak" TraceScopes which have been detached.  They will not
   * consume any resources other than a small amount of memory until they are
   * garbage collected.  On the other hand, trace scopes which are still
   * attached must never be leaked.
   */
  public void detach() {
  }

  /**
   * Attach this TraceScope to the current thread.
   */
  public void reattach() {
  }

  /**
   * Close this TraceScope, ending the trace span it is managing.
   */
  @Override
  public void close() {
  }

  public void addKVAnnotation(String key, String value) {
  }

  public void addTimelineAnnotation(String msg) {
  }

  static final class Holder {
    public static final TraceScope SINGLETON = new TraceScope();
  }

}
