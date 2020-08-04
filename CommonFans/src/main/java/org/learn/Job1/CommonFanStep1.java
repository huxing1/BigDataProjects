package org.learn.Job1;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.learn.Config.MapReduceConn;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-08-03 17:31
 **/
public class CommonFanStep1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        MapReduceConn conn = new MapReduceConn();
        Job job = conn.MRConn("MR任务：计算共同粉丝");
        job.setJarByClass(CommonFanStep1.class);
        job.setMapperClass(CommonFansStep1Mapper.class);
        job.setReducerClass(CommonFansStep1Reduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        // 设置数据输入输出路径
        Path inputPath = new Path("/hadoop/commonFansStep1");
        Path outputPath = new Path("/output/commonFansStep1");
        FileInputFormat.addInputPath(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);
        System.exit(job.waitForCompletion(true) ? 0 : 1);


    }
}
