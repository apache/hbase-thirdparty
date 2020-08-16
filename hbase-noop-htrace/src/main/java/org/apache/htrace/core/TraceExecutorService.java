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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A convenience wrapper around an {@link ExecutorService} for automatically
 * propagating trace scopes to executable tasks.
 */
public class TraceExecutorService implements ExecutorService {
  private final ExecutorService impl;

  TraceExecutorService(Tracer tracer, String scopeName,
                       ExecutorService impl) {
    this.impl = impl;
  }

  @Override
  public void execute(Runnable command) {
    impl.execute(command);
  }

  @Override
  public void shutdown() {
    impl.shutdown();
  }

  @Override
  public List<Runnable> shutdownNow() {
    return impl.shutdownNow();
  }

  @Override
  public boolean isShutdown() {
    return impl.isShutdown();
  }

  @Override
  public boolean isTerminated() {
    return impl.isTerminated();
  }

  @Override
  public boolean awaitTermination(long timeout, TimeUnit unit)
      throws InterruptedException {
    return impl.awaitTermination(timeout, unit);
  }

  @Override
  public <T> Future<T> submit(Callable<T> task) {
    return impl.submit(task);
  }

  @Override
  public <T> Future<T> submit(Runnable task, T result) {
    return impl.submit(task, result);
  }

  @Override
  public Future<?> submit(Runnable task) {
    return impl.submit(task);
  }

  @Override
  public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
      throws InterruptedException {
    return impl.invokeAll(tasks);
  }

  @Override
  public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                       long timeout, TimeUnit unit) throws InterruptedException {
    return impl.invokeAll(tasks, timeout, unit);
  }

  @Override
  public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
      throws InterruptedException, ExecutionException {
    return impl.invokeAny(tasks);
  }

  @Override
  public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout,
                         TimeUnit unit) throws InterruptedException, ExecutionException,
      TimeoutException {
    return impl.invokeAny(tasks, timeout, unit);
  }

}
