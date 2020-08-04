package org.learn.Job2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-08-04 17:18
 **/
public class CommonFansStep2Mapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split=value.toString().split("\t");
        context.write(new Text(split[0]),new Text(split[1]));
    }
}
