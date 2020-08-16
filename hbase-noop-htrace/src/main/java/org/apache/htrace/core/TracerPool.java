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
 * A pool of Tracer objects.
 *
 * There may be more than one {@link Tracer} running inside a single 'process'; for example,
 * unit tests may spin up a DataNode, a NameNode, and HDFS clients all running in a single JVM
 * instance, each with its own Tracer. TracerPool is where all Tracer instances register
 * on creation so Tracers can coordinate around shared resources such as {@link SpanReceiver}
 * instances. TracerPool takes care of properly cleaning up registered Tracer instances on shutdown.
 */
public class TracerPool {

  /**
   * The global pool of tracer objects.
   *
   * This is the pool that new tracers get put into by default.
   */
  static final TracerPool GLOBAL = new TracerPool("Global");

  /**
   * Get the global tracer pool.
   *
   * @return The tracer pool.
   */
  public static TracerPool getGlobalTracerPool() {
    return GLOBAL;
  }

  public TracerPool(String name) {
  }

  /**
   * Return the name of this TracerPool.
   *
   * @return The name.
   */
  public String getName() {
    return "";
  }

  /**
   * Returns an array of all the current span receivers.
   *
   * Note that if the current span receivers change, those changes will not be
   * reflected in this array.  In other words, this array may be stale.
   *
   * @return An array of the current span receivers.
   */
  public SpanReceiver[] getReceivers() {
    return new SpanReceiver[0];
  }

  /**
   * Add a new span receiver.
   *
   * @param receiver        The new receiver to add.
   *
   * @return                True if the new receiver was added; false if it
   *                          already was there.
   */
  public synchronized boolean addReceiver(SpanReceiver receiver) {
    // since we are not tracking these, maybe we should close them immediately?
    // for now do nothing, since we should just be getting empty receivers that do not do anything.
    return true;
  }

  /**
   * Remove a span receiver.
   *
   * @param receiver        The receiver to remove.
   *
   * @return                True if the receiver was removed; false if it
   *                          did not exist in this pool.
   */
  public synchronized boolean removeReceiver(SpanReceiver receiver) {
    return false;
  }

  /**
   * Remove and close a span receiver.
   *
   * @param receiver        The receiver to remove.
   *
   * @return                True if the receiver was removed; false if it
   *                          did not exist in this pool.
   */
  public boolean removeAndCloseReceiver(SpanReceiver receiver) {
    return false;
  }

  /**
   * Given a SpanReceiver class name, return the existing instance of that span
   * receiver, if possible; otherwise, invoke the callable to create a new
   * instance.
   *
   * @param className       The span receiver class name.
   * @param conf            The HTrace configuration.
   * @param classLoader     The class loader to use.
   *
   * @return                The SpanReceiver.
   */
  public synchronized SpanReceiver loadReceiverType(String className,
      HTraceConfiguration conf, ClassLoader classLoader) {
    SpanReceiver receiver = new SpanReceiver.Builder(conf).
        className(className).
        classLoader(classLoader).
        build();
    return receiver;
  }

  /**
   * Returns an array of all the current Tracers.
   *
   * Note that if the current Tracers change, those changes will not be
   * reflected in this array.  In other words, this array may be stale.
   *
   * @return The current array of tracers.
   */
  public synchronized Tracer[] getTracers() {
    return new Tracer[0];
  }

  /**
   * Add a new Tracer.
   */
  synchronized void addTracer(Tracer tracer) {
  }

  /**
   * Remove a Tracer.
   *
   * If the Tracer removed was the last one, we will close all the SpanReceiver
   * objects that we're managing.
   */
  synchronized void removeTracer(Tracer tracer) {
  }

}
