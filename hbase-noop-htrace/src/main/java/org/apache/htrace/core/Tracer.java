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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Use a Tracer instance inside a 'process' to collect and distribute its trace Spans.
 * Example processes are an HDFS DataNode or an HBase RegionServer. A Tracer instance is your
 * one-stop shop for all things tracing.
 *
 */
@SuppressWarnings("FinalClass")
public class Tracer implements Closeable {
  public final static String SPAN_RECEIVER_CLASSES_KEY = "span.receiver.classes";
  public final static String SAMPLER_CLASSES_KEY = "sampler.classes";

  public static class Builder {
    /**
     * @deprecated Since 4.0.0. Use Constructor that takes a <code>name</code> argument instead
     */
    @Deprecated
    public Builder() {
    }

    public Builder(final String name) {
    }

    /**
     * @param name The name of the Tracer to create.
     * @return this
     * @deprecated Since 4.0.0. Use Constructor that takes a <code>name</code> argument instead.
     */
    @Deprecated
    public Builder name(String name) {
      return this;
    }

    /**
     * @param conf The configuration to set.
     * @return this
     */
    public Builder conf(HTraceConfiguration conf) {
      return this;
    }

    /**
     * @param tracerPool The pool to set.
     * @return this
     */
    public Builder tracerPool(TracerPool tracerPool) {
      return this;
    }

    /**
     * @return The new Tracer object.
     */
    public Tracer build() {
      return SINGLETON;
    }
  }

  private final static Tracer SINGLETON = new Tracer();

  /**
   * @return If the current thread is tracing, this function returns the Tracer that is
   *   being used; otherwise, it returns null.
   */
  public static Tracer curThreadTracer() {
    return SINGLETON;
  }

  private Tracer() {
  }

  public String getTracerId() {
    return "";
  }

  /**
   * Create a new trace scope.
   *
   * If there are no scopes above the current scope, we will apply our
   * configured samplers. Otherwise, we will create a trace Span only if this thread
   * is already tracing, or if the passed parentID was valid.
   *
   * @param description         The description of the new span to create.
   * @param parentId            If this is a valid span ID, it will be added to
   *                              the parents of the new span we create.
   * @return                    The new trace scope.
   */
  public TraceScope newScope(String description, SpanId parentId) {
    return TraceScope.Holder.SINGLETON;
  }

  /**
   * Create a new trace scope.
   *
   * If there are no scopes above the current scope, we will apply our
   * configured samplers. Otherwise, we will create a trace Span only if this thread
   * is already tracing.
   * @param description         The description of the new span to create.
   * @return                    The new trace scope.
   */
  public TraceScope newScope(String description) {
    return TraceScope.Holder.SINGLETON;
  }

  /**
   * Return a null trace scope.
   *
   * @return The null trace scope.
   */
  public TraceScope newNullScope() {
    return TraceScope.Holder.SINGLETON;
  }

  /**
   * Wrap the callable in a TraceCallable, if tracing.
   *
   * @param <V>          The subclass of callable.
   * @param callable     The callable to wrap.
   * @param description  A description of the callable, or null if there
   *                     is no description.
   * @return The callable provided, wrapped if tracing, 'callable' if not.
   */
  public <V> Callable<V> wrap(Callable<V> callable, String description) {
    return callable;
  }

  /**
   * Wrap the runnable in a TraceRunnable, if tracing
   *
   * @param runnable      The runnable to wrap.
   * @param description   A description of the runnable, or null if there is
   *                      no description.
   * @return The runnable provided, wrapped if tracing, 'runnable' if not.
   */
  public Runnable wrap(Runnable runnable, String description) {
    return runnable;
  }

  public TraceExecutorService newTraceExecutorService(ExecutorService impl) {
    return newTraceExecutorService(impl, null);
  }

  public TraceExecutorService newTraceExecutorService(ExecutorService impl,
                                                      String scopeName) {
    return new TraceExecutorService(this, scopeName, impl);
  }

  public ScheduledTraceExecutorService newTraceExecutorService(
      ScheduledExecutorService impl) {
    return newTraceExecutorService(impl, null);
  }

  public ScheduledTraceExecutorService newTraceExecutorService(
      ScheduledExecutorService impl, String scopeName) {
    return new ScheduledTraceExecutorService(this, scopeName, impl);
  }

  public TracerPool getTracerPool() {
    return TracerPool.getGlobalTracerPool();
  }

  /**
   * Returns an array of all the current Samplers.
   *
   * Note that if the current Samplers change, those changes will not be
   * reflected in this array.  In other words, this array may be stale.
   *
   * @return The current samplers.
   */
  public Sampler[] getSamplers() {
    return new Sampler[0];
  }

  /**
   * Add a new Sampler.
   *
   * @param sampler       The new sampler to add.
   *                      You cannot add a particular Sampler object more
   *                        than once.  You may add multiple Sampler objects
   *                        of the same type, although this is not recommended.
   *
   * @return              True if the sampler was added; false if it already had
   *                        been added earlier.
   */
  public synchronized boolean addSampler(Sampler sampler) {
    return true;
  }

  /**
   * Remove a Sampler.
   *
   * @param sampler       The sampler to remove.
   * @return              True only if the sampler was removed.
   */
  public synchronized boolean removeSampler(Sampler sampler) {
    return false;
  }

  public static Span getCurrentSpan() {
    return null;
  }

  public static SpanId getCurrentSpanId() {
    return SpanId.INVALID;
  }

  @Override
  public synchronized void close() {
  }
}
