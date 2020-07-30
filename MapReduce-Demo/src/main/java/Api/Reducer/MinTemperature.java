package Api.Reducer;

import Api.Mapper.TemperatureMapper;
import Config.MapReduceConn;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-07-29 15:24
 **/
public class MinTemperature {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        if (args.length!=2){
//            System.out.println("Usage: MinTemperature <input path> <output path>");
//            System.exit(-1);
//        }

        MapReduceConn mapReduceConn = new MapReduceConn();
        Job job= mapReduceConn.MRConn("MapReduce实验-气象数据集-求气温最小值");

//        Job job = Job.getInstance();
        job.setJarByClass(MinTemperature.class);
//        job.setJobName("MapReduce实验-气象数据集-求气温最小值");
        //windows下执行
        Path inputPath = new Path("/hadoop/weatherData0728");
        Path outputPath = new Path("/output/weatherdata/min");
        FileInputFormat.addInputPath(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);
        //linux中参数执行
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(TemperatureMapper.class);
        job.setReducerClass(MinTemperatureReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
