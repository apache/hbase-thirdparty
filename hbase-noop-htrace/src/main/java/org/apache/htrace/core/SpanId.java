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
 * Uniquely identifies an HTrace span.
 *
 * Span IDs are 128 bits in total.  The upper 64 bits of a span ID is the same
 * as the upper 64 bits of the parent span, if there is one.  The lower 64 bits
 * are always random.
 *
 * * All Span IDs are 0.
 */
public final class SpanId implements Comparable<SpanId> {

  /**
   * The invalid span ID, which is all zeroes.
   *
   * It is also the "least" span ID in the sense that it is considered
   * smaller than any other span ID.
   */
  @SuppressWarnings("VisibilityModifier")
  public static SpanId INVALID = new SpanId(0, 0);


  public static SpanId fromRandom() {
    return INVALID;
  }

  public static SpanId fromString(String str) {
    return INVALID;
  }

  public SpanId(long high, long low) {
  }

  public long getHigh() {
    return 0L;
  }

  public long getLow() {
    return 0L;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SpanId)) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(SpanId other) {
    return 0;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public String toString() {
    return "00000000000000000000000000000000";
  }

  public boolean isValid() {
    return false;
  }

  public SpanId newChildId() {
    return INVALID;
  }
}
