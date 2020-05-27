package org.learn.Config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.Properties;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-05-26 14:13
 **/
public class HDFSConn {

    public FileSystem initHDFS() throws IOException {
        //hadoop在访问hdfs的时候会进行权限认证，取用户名的过程是这样的：
        //读取HADOOP_USER_NAME系统环境变量，如果不为空，那么拿它作username，如果为空
        //读取HADOOP_USER_NAME这个java环境变量，如果为空
        //从com.sun.security.auth.NTUserPrincipal或者com.sun.security.auth.UnixPrincipal的实例获取username
        //如果以上尝试都失败，那么抛出异常LoginException("Can’t find user name")

        Properties properties = System.getProperties();
        properties.setProperty("HADOOP_USER_NAME", "root");

        Configuration conf=new Configuration();
        conf.addResource("hdfs-site.xml");
        conf.addResource("core-site.xml");
        FileSystem fileSystem = FileSystem.get(conf);
        return fileSystem;
    }
}
