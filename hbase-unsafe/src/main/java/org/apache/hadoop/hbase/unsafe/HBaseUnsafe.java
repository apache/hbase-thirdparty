/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delegate all methods of {@link HBaseUnsafe0}, so we will not touch the actual
 * {@code sun.misc.Unsafe} class until we actually call the methods.
 */
public final class HBaseUnsafe {

  private static final String CLASS_NAME = "sun.misc.Unsafe";
  private static final Logger LOG = LoggerFactory.getLogger(HBaseUnsafe.class);
  private static final boolean AVAIL;
  private static final boolean UNALIGNED;

  static {
    AVAIL = AccessController.doPrivileged(new PrivilegedAction<Boolean>() {

      @Override
      public Boolean run() {
        return checkAvailable();
      }
    });
    UNALIGNED = checkUnaligned();
  }

  private static boolean checkAvailable() {
    try {
      Class<?> clazz = Class.forName(CLASS_NAME);
      Field f = clazz.getDeclaredField("theUnsafe");
      f.setAccessible(true);
      Object theUnsafe = f.get(null);
      if (theUnsafe == null) {
        LOG.warn("Could not get static instance from sun.misc.Unsafe");
        return false;
      }
      // Check for availability of all methods used by UnsafeAccess
      Method m;
      try {
        m = clazz.getDeclaredMethod("arrayBaseOffset", Class.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing arrayBaseOffset(Class)");
          return false;
        }
        m = clazz.getDeclaredMethod("copyMemory", Object.class, long.class, Object.class,
          long.class, long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing copyMemory(Object,long,Object,long,long)");
          return false;
        }
        m = clazz.getDeclaredMethod("getByte", Object.class, long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing getByte(Object,long)");
          return false;
        }
        m = clazz.getDeclaredMethod("getShort", long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing getShort(long)");
          return false;
        }
        m = clazz.getDeclaredMethod("getShort", Object.class, long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing getShort(Object,long)");
          return false;
        }
        m = clazz.getDeclaredMethod("getInt", long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing getInt(long)");
          return false;
        }
        m = clazz.getDeclaredMethod("getInt", Object.class, long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing getInt(Object,long)");
          return false;
        }
        m = clazz.getDeclaredMethod("getLong", long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing getLong(long)");
          return false;
        }
        m = clazz.getDeclaredMethod("getLong", Object.class, long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing getLong(Object,long)");
          return false;
        }
        m = clazz.getDeclaredMethod("putByte", long.class, byte.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putByte(long,byte)");
          return false;
        }
        m = clazz.getDeclaredMethod("putByte", Object.class, long.class, byte.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putByte(Object,long,byte)");
          return false;
        }
        m = clazz.getDeclaredMethod("putShort", long.class, short.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putShort(long,short)");
          return false;
        }
        m = clazz.getDeclaredMethod("putShort", Object.class, long.class, short.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putShort(Object,long,short)");
          return false;
        }
        m = clazz.getDeclaredMethod("putInt", long.class, int.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putInt(long,int)");
          return false;
        }
        m = clazz.getDeclaredMethod("putInt", Object.class, long.class, int.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putInt(Object,long,int)");
          return false;
        }
        m = clazz.getDeclaredMethod("putLong", long.class, long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putLong(long,long)");
          return false;
        }
        m = clazz.getDeclaredMethod("putLong", Object.class, long.class, long.class);
        if (m == null) {
          LOG.warn("sun.misc.Unsafe is missing putLong(Object,long,long)");
          return false;
        }
        // theUnsafe is accessible and all methods are available
        return true;
      } catch (Throwable e) {
        LOG.warn("sun.misc.Unsafe is missing one or more required methods", e);
      }
    } catch (Throwable e) {
      LOG.warn("sun.misc.Unsafe is not available/accessible", e);
    }
    return false;
  }

  private static boolean checkUnaligned() {
    // When Unsafe itself is not available/accessible consider unaligned as false.
    if (!AVAIL) {
      return false;
    }
    String arch = System.getProperty("os.arch");
    if ("ppc64".equals(arch) || "ppc64le".equals(arch) || "aarch64".equals(arch)) {
      // java.nio.Bits.unaligned() wrongly returns false on ppc (JDK-8165231),
      return true;
    }
    try {
      // Using java.nio.Bits#unaligned() to check for unaligned-access capability
      Class<?> clazz = Class.forName("java.nio.Bits");
      Method m = clazz.getDeclaredMethod("unaligned");
      m.setAccessible(true);
      return (Boolean) m.invoke(null);
    } catch (Exception e) {
      LOG.warn("java.nio.Bits#unaligned() check failed."
          + "Unsafe based read/write of primitive types won't be used",
        e);
    }
    return false;
  }

  /**
   * @return true when running JVM is having sun's Unsafe package available in it and it is
   *         accessible.
   */
  public static boolean isAvailable() {
    return AVAIL;
  }

  /**
   * @return true when running JVM is having sun's Unsafe package available in it and underlying
   *         system having unaligned-access capability.
   */
  public static boolean unaligned() {
    return UNALIGNED;
  }

  private HBaseUnsafe() {
    // private constructor to avoid instantiation
  }

  public static int getInt(Object o, long offset) {
    return HBaseUnsafe0.getInt(o, offset);
  }

  public static void putInt(Object o, long offset, int x) {
    HBaseUnsafe0.putInt(o, offset, x);
  }

  public static Object getObject(Object o, long offset) {
    return HBaseUnsafe0.getObject(o, offset);
  }

  public static void putObject(Object o, long offset, Object x) {
    HBaseUnsafe0.putObject(o, offset, x);
  }

  public static boolean getBoolean(Object o, long offset) {
    return HBaseUnsafe0.getBoolean(o, offset);
  }

  public static void putBoolean(Object o, long offset, boolean x) {
    HBaseUnsafe0.putBoolean(o, offset, x);
  }

  public static byte getByte(Object o, long offset) {
    return HBaseUnsafe0.getByte(o, offset);
  }

  public static void putByte(Object o, long offset, byte x) {
    HBaseUnsafe0.putByte(o, offset, x);
  }

  public static short getShort(Object o, long offset) {
    return HBaseUnsafe0.getShort(o, offset);
  }

  public static void putShort(Object o, long offset, short x) {
    HBaseUnsafe0.putShort(o, offset, x);
  }

  public static char getChar(Object o, long offset) {
    return HBaseUnsafe0.getChar(o, offset);
  }

  public static void putChar(Object o, long offset, char x) {
    HBaseUnsafe0.putChar(o, offset, x);
  }

  public static long getLong(Object o, long offset) {
    return HBaseUnsafe0.getLong(o, offset);
  }

  public static void putLong(Object o, long offset, long x) {
    HBaseUnsafe0.putLong(o, offset, x);
  }

  public static float getFloat(Object o, long offset) {
    return HBaseUnsafe0.getFloat(o, offset);
  }

  public static void putFloat(Object o, long offset, float x) {
    HBaseUnsafe0.putFloat(o, offset, x);
  }

  public static double getDouble(Object o, long offset) {
    return HBaseUnsafe0.getDouble(o, offset);
  }

  public static void putDouble(Object o, long offset, double x) {
    HBaseUnsafe0.putDouble(o, offset, x);
  }

  public static byte getByte(long address) {
    return HBaseUnsafe0.getByte(address);
  }

  public static void putByte(long address, byte x) {
    HBaseUnsafe0.putByte(address, x);
  }

  public static short getShort(long address) {
    return HBaseUnsafe0.getShort(address);
  }

  public static void putShort(long address, short x) {
    HBaseUnsafe0.putShort(address, x);
  }

  public static char getChar(long address) {
    return HBaseUnsafe0.getChar(address);
  }

  public static void putChar(long address, char x) {
    HBaseUnsafe0.putChar(address, x);
  }

  public static int getInt(long address) {
    return HBaseUnsafe0.getInt(address);
  }

  public static void putInt(long address, int x) {
    HBaseUnsafe0.putInt(address, x);
  }

  public static long getLong(long address) {
    return HBaseUnsafe0.getLong(address);
  }

  public static void putLong(long address, long x) {
    HBaseUnsafe0.putLong(address, x);
  }

  public static float getFloat(long address) {
    return HBaseUnsafe0.getFloat(address);
  }

  public static void putFloat(long address, float x) {
    HBaseUnsafe0.putFloat(address, x);
  }

  public static double getDouble(long address) {
    return HBaseUnsafe0.getDouble(address);
  }

  public static void putDouble(long address, double x) {
    HBaseUnsafe0.putDouble(address, x);
  }

  public static long getAddress(long address) {
    return HBaseUnsafe0.getAddress(address);
  }

  public static void putAddress(long address, long x) {
    HBaseUnsafe0.putAddress(address, x);
  }

  public static long allocateMemory(long bytes) {
    return HBaseUnsafe0.allocateMemory(bytes);
  }

  public static long reallocateMemory(long address, long bytes) {
    return HBaseUnsafe0.reallocateMemory(address, bytes);
  }

  public static void setMemory(Object o, long offset, long bytes, byte value) {
    HBaseUnsafe0.setMemory(o, offset, bytes, value);
  }

  public static void setMemory(long address, long bytes, byte value) {
    HBaseUnsafe0.setMemory(address, bytes, value);
  }

  public static void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset,
      long bytes) {
    HBaseUnsafe0.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
  }

  public static void copyMemory(long srcAddress, long destAddress, long bytes) {
    HBaseUnsafe0.copyMemory(srcAddress, destAddress, bytes);
  }

  public static void freeMemory(long address) {
    HBaseUnsafe0.freeMemory(address);
  }

  public static long staticFieldOffset(Field f) {
    return HBaseUnsafe0.staticFieldOffset(f);
  }

  public static long objectFieldOffset(Field f) {
    return HBaseUnsafe0.objectFieldOffset(f);
  }

  public static Object staticFieldBase(Field f) {
    return HBaseUnsafe0.staticFieldBase(f);
  }

  public static boolean shouldBeInitialized(Class<?> c) {
    return HBaseUnsafe0.shouldBeInitialized(c);
  }

  public static void ensureClassInitialized(Class<?> c) {
    HBaseUnsafe0.ensureClassInitialized(c);
  }

  public static int arrayBaseOffset(Class<?> arrayClass) {
    return HBaseUnsafe0.arrayBaseOffset(arrayClass);
  }

  public static int arrayIndexScale(Class<?> arrayClass) {
    return HBaseUnsafe0.arrayIndexScale(arrayClass);
  }

  public static int addressSize() {
    return HBaseUnsafe0.addressSize();
  }

  public static int pageSize() {
    return HBaseUnsafe0.pageSize();
  }

  public static Class<?> defineClass(String name, byte[] b, int off, int len, ClassLoader loader,
      ProtectionDomain protectionDomain) {
    return HBaseUnsafe0.defineClass(name, b, off, len, loader, protectionDomain);
  }

  public static Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches) {
    return HBaseUnsafe0.defineAnonymousClass(hostClass, data, cpPatches);
  }

  public static Object allocateInstance(Class<?> cls) throws InstantiationException {
    return HBaseUnsafe0.allocateInstance(cls);
  }

  public static void throwException(Throwable ee) {
    HBaseUnsafe0.throwException(ee);
  }

  public static boolean compareAndSwapObject(Object o, long offset, Object expected, Object x) {
    return HBaseUnsafe0.compareAndSwapObject(o, offset, expected, x);
  }

  public static boolean compareAndSwapInt(Object o, long offset, int expected, int x) {
    return HBaseUnsafe0.compareAndSwapInt(o, offset, expected, x);
  }

  public static boolean compareAndSwapLong(Object o, long offset, long expected, long x) {
    return HBaseUnsafe0.compareAndSwapLong(o, offset, expected, x);
  }

  public static Object getObjectVolatile(Object o, long offset) {
    return HBaseUnsafe0.getObjectVolatile(o, offset);
  }

  public static void putObjectVolatile(Object o, long offset, Object x) {
    HBaseUnsafe0.putObjectVolatile(o, offset, x);
  }

  public static int getIntVolatile(Object o, long offset) {
    return HBaseUnsafe0.getIntVolatile(o, offset);
  }

  public static void putIntVolatile(Object o, long offset, int x) {
    HBaseUnsafe0.putIntVolatile(o, offset, x);
  }

  public static boolean getBooleanVolatile(Object o, long offset) {
    return HBaseUnsafe0.getBooleanVolatile(o, offset);
  }

  public static void putBooleanVolatile(Object o, long offset, boolean x) {
    HBaseUnsafe0.putBooleanVolatile(o, offset, x);
  }

  public static byte getByteVolatile(Object o, long offset) {
    return HBaseUnsafe0.getByteVolatile(o, offset);
  }

  public static void putByteVolatile(Object o, long offset, byte x) {
    HBaseUnsafe0.putByteVolatile(o, offset, x);
  }

  public static short getShortVolatile(Object o, long offset) {
    return HBaseUnsafe0.getShortVolatile(o, offset);
  }

  public static void putShortVolatile(Object o, long offset, short x) {
    HBaseUnsafe0.putShortVolatile(o, offset, x);
  }

  public static char getCharVolatile(Object o, long offset) {
    return HBaseUnsafe0.getCharVolatile(o, offset);
  }

  public static void putCharVolatile(Object o, long offset, char x) {
    HBaseUnsafe0.putCharVolatile(o, offset, x);
  }

  public static long getLongVolatile(Object o, long offset) {
    return HBaseUnsafe0.getLongVolatile(o, offset);
  }

  public static void putLongVolatile(Object o, long offset, long x) {
    HBaseUnsafe0.putLongVolatile(o, offset, x);
  }

  public static float getFloatVolatile(Object o, long offset) {
    return HBaseUnsafe0.getFloatVolatile(o, offset);
  }

  public static void putFloatVolatile(Object o, long offset, float x) {
    HBaseUnsafe0.putFloatVolatile(o, offset, x);
  }

  public static double getDoubleVolatile(Object o, long offset) {
    return HBaseUnsafe0.getDoubleVolatile(o, offset);
  }

  public static void putDoubleVolatile(Object o, long offset, double x) {
    HBaseUnsafe0.putDoubleVolatile(o, offset, x);
  }

  public static void putOrderedObject(Object o, long offset, Object x) {
    HBaseUnsafe0.putOrderedObject(o, offset, x);
  }

  public static void putOrderedInt(Object o, long offset, int x) {
    HBaseUnsafe0.putOrderedInt(o, offset, x);
  }

  public static void putOrderedLong(Object o, long offset, long x) {
    HBaseUnsafe0.putOrderedLong(o, offset, x);
  }

  public static void unpark(Object thread) {
    HBaseUnsafe0.unpark(thread);
  }

  public static void park(boolean isAbsolute, long time) {
    HBaseUnsafe0.park(isAbsolute, time);
  }

  public static int getLoadAverage(double[] loadavg, int nelems) {
    return HBaseUnsafe0.getLoadAverage(loadavg, nelems);
  }

  public static int getAndAddInt(Object o, long offset, int delta) {
    return HBaseUnsafe0.getAndAddInt(o, offset, delta);
  }

  public static long getAndAddLong(Object o, long offset, long delta) {
    return HBaseUnsafe0.getAndAddLong(o, offset, delta);
  }

  public static int getAndSetInt(Object o, long offset, int newValue) {
    return HBaseUnsafe0.getAndSetInt(o, offset, newValue);
  }

  public static long getAndSetLong(Object o, long offset, long newValue) {
    return HBaseUnsafe0.getAndSetLong(o, offset, newValue);
  }

  public static Object getAndSetObject(Object o, long offset, Object newValue) {
    return HBaseUnsafe0.getAndSetObject(o, offset, newValue);
  }

  public static void loadFence() {
    HBaseUnsafe0.loadFence();
  }

  public static void storeFence() {
    HBaseUnsafe0.storeFence();
  }

  public static void fullFence() {
    HBaseUnsafe0.fullFence();
  }

}
