package org.learn.Api;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.learn.Config.HbaseConn;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @program: BigDataProjects
 * @description: demo
 * @author: hux
 * @create: 2020-05-26 11:31
 **/
public class HBaseDemo {
    private static void createTable(String tbName,String famliyName) throws URISyntaxException, IOException {

        HbaseConn hbaseConn = new HbaseConn();
        Admin admin = hbaseConn.connectionWithResources().getAdmin();
        TableName tableName = TableName.valueOf(tbName);
        if (admin.tableExists(tableName)){
            System.out.println(tbName+"已存在...");
            return;
        }
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
        ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(famliyName)).build();
        tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
        admin.createTable(tableDescriptorBuilder.build());
    }



    public static void main(String[] args) throws IOException, URISyntaxException {

        //创建表
        String tableName="userInfo_table";
        String familySt= "family1";
        createTable(tableName,familySt);
        System.out.println(tableName+"创建完成！");
    }
}
