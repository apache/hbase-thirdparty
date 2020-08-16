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

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A Span implementation that stores its information in milliseconds since the
 * epoch.
 */
public class MilliSpan implements Span {

  @Override
  public Span child(String childDescription) {
    return Holder.SINGLETON;
  }

  /**
   * The public interface for constructing a MilliSpan.
   */
  public static class Builder {

    public Builder() {
    }

    public Builder begin(long begin) {
      return this;
    }

    public Builder end(long end) {
      return this;
    }

    public Builder description(String description) {
      return this;
    }

    public Builder parents(SpanId[] parents) {
      return this;
    }

    public Builder parents(List<SpanId> parentList) {
      return this;
    }

    public Builder spanId(SpanId spanId) {
      return this;
    }

    public Builder traceInfo(Map<String, String> traceInfo) {
      return this;
    }

    public Builder tracerId(String tracerId) {
      return this;
    }

    public Builder timeline(List<TimelineAnnotation> timeline) {
      return this;
    }

    public MilliSpan build() {
      return Holder.SINGLETON;
    }
  }

  public MilliSpan() {
  }

  @Override
  public synchronized void stop() {
  }

  protected long currentTimeMillis() {
    return System.currentTimeMillis();
  }

  @Override
  public synchronized boolean isRunning() {
    return false;
  }

  @Override
  public synchronized long getAccumulatedMillis() {
    return 0;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public String getDescription() {
    return "";
  }

  @Override
  public SpanId getSpanId() {
    return SpanId.INVALID;
  }

  @Override
  public SpanId[] getParents() {
    return new SpanId[0];
  }

  @Override
  public void setParents(SpanId[] parents) {
  }

  @Override
  public long getStartTimeMillis() {
    return 0;
  }

  @Override
  public long getStopTimeMillis() {
    return 0;
  }

  @Override
  public void addKVAnnotation(String key, String value) {
  }

  @Override
  public void addTimelineAnnotation(String msg) {
  }

  @Override
  public Map<String, String> getKVAnnotations() {
    return Collections.emptyMap();
  }

  @Override
  public List<TimelineAnnotation> getTimelineAnnotations() {
    return Collections.emptyList();
  }

  @Override
  public String getTracerId() {
    return "";
  }

  @Override
  public void setTracerId(String tracerId) {
  }

  @Override
  public String toJson() {
    return "";
  }

  /**
   * N.B. had to break compatibility here because a jackson class was in the class heirarchy and
   * method signature
   */
  public static class MilliSpanDeserializer {
  }

  public static MilliSpan fromJson(String json) throws IOException {
    return Holder.SINGLETON;
  }

  private static final class Holder {
    public static final MilliSpan SINGLETON = new MilliSpan();
  }
}
