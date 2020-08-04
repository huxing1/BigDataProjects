package org.learn.Job1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-07-30 17:08
 **/
public class CommonFansStep1Mapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(":");
        String user=splits[0];
        String[] fans=splits[1].split(",");

        for (String fan:fans){
            //K,V
            context.write(new Text(fan),new Text(user));
        }
    }
}
