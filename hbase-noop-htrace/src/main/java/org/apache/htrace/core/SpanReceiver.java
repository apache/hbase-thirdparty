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
 * The collector within a process that is the destination of Spans when a
 * trace is running. {@code SpanReceiver} implementations are expected to
 * provide a constructor with the signature
 *
 * <pre>
 * <code>public SpanReceiverImpl(HTraceConfiguration)</code>
 * </pre>
 */
public abstract class SpanReceiver implements Closeable {
  /**
   * A {@link SpanReceiver} builder. It takes a {@link SpanReceiver} class name
   * and constructs an instance of that class, with the provided configuration.
   */
  public static class Builder {

    public Builder(HTraceConfiguration conf) {
    }

    /**
     * Set this builder back to defaults.
     *
     * @return this instance.
     */
    public Builder reset() {
      return this;
    }

    public Builder className(final String className) {
      return this;
    }

    /**
     * Configure whether we should log errors during build().
     *
     * @param logErrors Whether we should log errors during build().
     * @return This instance
     */
    public Builder logErrors(boolean logErrors) {
      return this;
    }

    public Builder classLoader(ClassLoader classLoader) {
      return this;
    }

    public SpanReceiver build() {
      return Holder.SINGLETON;
    }
  }

  /**
   * Get the ID for this SpanReceiver.
   *
   * @return The unique ID identifying this SpanReceiver.
   */
  public final long getId() {
    return 0xdeadbeefL;
  }

  protected SpanReceiver() {
  }

  /**
   * Called when a Span is stopped and can now be stored.
   *
   * @param span    The span to store with this SpanReceiver.
   */
  public abstract void receiveSpan(Span span);

  private static final class Holder {
    static final SpanReceiver SINGLETON = new SpanReceiver() {
      @Override
      public void receiveSpan(Span span) {
      }
      @Override
      public void close() {
      }
    };
  }
}
