package org.learn.Job2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-08-04 17:38
 **/
public class CommonFansStep2Reducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        for (Text text:values){
            stringBuffer.append(text).append(",");
        }
        context.write(key,new Text(stringBuffer.substring(0,stringBuffer.length()-1)));
    }
}
