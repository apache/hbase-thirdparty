
package org.apache.hbase.htrace_noop;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHBaseTestingUtilityWithHTraceNoop {
  private static final HBaseTestingUtility TEST_UTIL = new HBaseTestingUtility();

  @BeforeClass
  public static void setUp() throws Exception {
    TEST_UTIL.startMiniCluster();
  }

  @AfterClass
  public static void tearDown() throws Exception {
    TEST_UTIL.shutdownMiniCluster();
  }

  @Test
  public void testPutThenCountWithNewTable() throws Exception {
    TableName tableName = TableName.valueOf("test");

    Table table = TEST_UTIL.createTable(tableName, "cf");

    Put put1 = new Put(Bytes.toBytes("r1"));
    put1.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("c"), Bytes.toBytes(1));
    table.put(put1);

    Put put2 = new Put(Bytes.toBytes("r2"));
    put2.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("c"), Bytes.toBytes(2));
    table.put(put2);

    int rows = TEST_UTIL.countRows(tableName);
    assertEquals(2, rows);
  }
}
