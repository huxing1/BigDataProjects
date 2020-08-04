package org.learn.Job2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.learn.Config.MapReduceConn;
import org.learn.Job1.CommonFanStep1;
import org.learn.Job1.CommonFansStep1Mapper;
import org.learn.Job1.CommonFansStep1Reduce;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-08-04 17:45
 **/
public class CommonFansStep2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        MapReduceConn conn = new MapReduceConn();
        Job job = conn.MRConn("MR任务：计算共同粉丝2");
        job.setJarByClass(CommonFansStep2.class);
        job.setMapperClass(CommonFansStep2Mapper.class);
        job.setReducerClass(CommonFansStep2Reducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        // 设置数据输入输出路径
        Path inputPath = new Path("/output/commonFansStep1/part-r-00000");
        Path outputPath = new Path("/output/commonFansStep2");
        FileInputFormat.addInputPath(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
