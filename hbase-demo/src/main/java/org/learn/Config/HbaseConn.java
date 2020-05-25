package org.learn.Config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @program: BigDataModule
 * @description: https://www.cnblogs.com/littleatp/p/12013982.html
 * https://blog.csdn.net/liubenlong007/article/details/96453098
 * @author: hux
 * @create: 2020-05-21 19:41
 **/
public class HbaseConn {
    public Connection connectionWithResources() throws URISyntaxException {
        Configuration configuration= HBaseConfiguration.create();
        configuration.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        configuration.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));
        Connection connection=null;
        try {
            //创建连接
             connection = ConnectionFactory.createConnection(configuration);
            //获得执行操作的管理接口
//            Admin admin = connection.getAdmin();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
