package org.learn.Utils;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.learn.Config.HbaseConn;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * @program: BigDataModule
 * @description:
 * @author: hux
 * @create: 2020-05-21 20:06
 **/
public class HBaseUtil {
//TODO 分页查询，批量写入，（HDFS）等等

    /***
    * @Description: 创建表
     * https://www.jianshu.com/p/7b9012e901be
    * @Param: [tableName, cfs]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/22
    */
    public void createTable(String tableName,String... cfs ) throws URISyntaxException, IOException {  //String...表示不定参数，可以传入多个string对象

        HbaseConn hbaseConn = new HbaseConn();
        Admin admin = hbaseConn.connectionWithResources().getAdmin();
        TableName tbName = TableName.valueOf(tableName);

        if (admin.tableExists(tbName)){
            System.out.println(String.format("表 %s 已存在",tableName));
            return;
        }
        //hbase1.x中的构建表对象方法是HTableDescriptor，2.x中已弃用（但是也能用）
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tbName);
        for (String cf:cfs){
            ColumnFamilyDescriptor famliy = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build();
            //设置列簇
            tableDescriptorBuilder.setColumnFamily(famliy);
        }
        admin.createTable(tableDescriptorBuilder.build());
    }

    /**
    * @Description: 删除表
    * @Param: [tableName]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/25
    */
    public void deleteTable(String tableName) throws URISyntaxException, IOException {
        HbaseConn hbaseConn = new HbaseConn();
        Admin admin = hbaseConn.connectionWithResources().getAdmin();
        TableName tbName = TableName.valueOf(tableName);
        if (!admin.tableExists(tbName)){
            System.out.println(String.format("表 %s 不存在",tableName));
            return;
        }
        admin.disableTable(tbName);
        admin.deleteTable(tbName);
        System.out.println(String.format("表 %s 已删除",tableName));
    }

  /**
  * @Description: 写入数据
  * @Param: [tableName, rowkey, famliy, qualifier, value]
  * @return: void
  * @Author: hux
  * @Date: 2020/5/25
  */
    public void putData(String tableName,String rowkey,String famliy,String qualifier,String value) throws URISyntaxException, IOException {
        HbaseConn hbaseConn = new HbaseConn();
        TableName tbName=TableName.valueOf(tableName);
        Table table = hbaseConn.connectionWithResources().getTable(tbName);
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(famliy),Bytes.toBytes(qualifier),Bytes.toBytes((value)));
        table.put(put);
        System.out.println("数据插入成功，插入："+put.toString());
    }

    /**
    * @Description: 查询数据
    * @Param: [tableName, rowkey]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/25
    */
    public void getData(String tableName,String rowkey) throws URISyntaxException, IOException {
        HbaseConn hbaseConn = new HbaseConn();
        TableName tbName=TableName.valueOf(tableName);
        Table table = hbaseConn.connectionWithResources().getTable(tbName);
        Get get = new Get(Bytes.toBytes(rowkey));
        Result result = table.get(get);
        CellScanner cellScanner = result.cellScanner();
        while (cellScanner.advance()){
            Cell cell = cellScanner.current();
            String row=Bytes.toString(CellUtil.cloneRow(cell));
            String famliy = Bytes.toString(CellUtil.cloneFamily(cell));
            String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println(String.format("[%s,%s,%s,%s]",row,famliy,qualifier,value));

        }
    }

   /**
   * @Description: 删除某条数据
   * @Param: [tableName, rowkey, family, qualifier]
   * @return: void
   * @Author: hux
   * @Date: 2020/5/25
   */
    public void deleteData(String tableName,String rowkey,String family, String qualifier) throws URISyntaxException, IOException {
        HbaseConn hbaseConn = new HbaseConn();
        TableName tbName=TableName.valueOf(tableName);
        Table table = hbaseConn.connectionWithResources().getTable(tbName);
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        delete.addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier));
        table.delete(delete);
        System.out.println(String.format("删除数据 [%s,%s,%s,%s]",tableName,rowkey,family,qualifier));
    }

    /**
    * @Description: 删除某行数据
    * @Param: [tableName, rowkey]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/25
    */
    public void deleteRow(String tableName,String rowkey) throws URISyntaxException, IOException {
        HbaseConn hbaseConn = new HbaseConn();
        TableName tbName = TableName.valueOf(tableName);
        Table table = hbaseConn.connectionWithResources().getTable(tbName);
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        table.delete(delete);
    }
}
