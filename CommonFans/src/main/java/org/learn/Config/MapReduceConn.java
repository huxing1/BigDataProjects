package org.learn.Config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-05-28 19:00
 **/
public class MapReduceConn {

    public Job MRConn(String JobName) throws IOException {
        Job job = Job.getInstance();
        job.setJobName(JobName);
        Configuration conf = job.getConfiguration();
        conf.addResource("mapred-site.xml");
        conf.addResource("yarn-site.xml");
        conf.addResource("core-site.xml");
        conf.addResource("hdfs-site.xml");
        // 也可以在这里设置编译好的jar
        conf.set("mapred.jar", "D:\\文档书籍代码\\coding\\A-learn\\BigDataProjects\\CommonFans\\target\\CommonFans-1.0-SNAPSHOT.jar");
        // Windows开发者需要设置跨平台
        conf.set("mapreduce.app-submission.cross-platform", "true");
        return job;
    }
}
