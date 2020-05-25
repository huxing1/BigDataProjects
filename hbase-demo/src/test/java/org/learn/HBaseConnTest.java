package org.learn;

import org.apache.hadoop.hbase.client.Connection;
import org.learn.Config.HbaseConn;
import org.learn.Utils.HBaseUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @program: BigDataModule
 * @description:
 * @author: hux
 * @create: 2020-05-21 20:09
 **/
public class HBaseConnTest {
    @Test
    public void getConnTest() throws URISyntaxException, IOException {
        HbaseConn hbaseConn = new HbaseConn();
        Connection conn = hbaseConn.connectionWithResources();
        System.out.println(!conn.isClosed());
    }

    @Test
    public void createTest() throws IOException, URISyntaxException {
        HBaseUtil hBaseUtil = new HBaseUtil();
        String tablename = "testTable0525";
        hBaseUtil.createTable(tablename, "info", "focus");
        System.out.println("创建表成功");
    }

    @Test
    public void deleteTest() throws IOException, URISyntaxException {
        HBaseUtil hBaseUtil = new HBaseUtil();
        hBaseUtil.deleteTable("test0525");
    }

    @Test
    public void putDataTest() throws IOException, URISyntaxException {
        HBaseUtil hBaseUtil = new HBaseUtil();
        hBaseUtil.putData("testTable0525", "111", "info", "sex", "nv");
    }

    @Test
    public void getDataTest() throws IOException, URISyntaxException {
        HBaseUtil hBaseUtil = new HBaseUtil();
        hBaseUtil.getData("testTable0525", "111");
    }

    @Test
    public void deleteDataTest() throws IOException, URISyntaxException {
        HBaseUtil hBaseUtil = new HBaseUtil();
        hBaseUtil.deleteData("testTable0525", "111", "info", "sex");
    }

    @Test
    public void deleteRowTest() throws IOException, URISyntaxException {
        HBaseUtil hBaseUtil = new HBaseUtil();
        hBaseUtil.deleteRow("testTable0525","111");
    }
}
