package api;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import scala.Tuple2;

import java.io.IOException;
import java.util.List;

/**
 * @program: BigDataProjects
 * @description: 基于yarn cluster的提交模式
 * spark-submit --master yarn --deploy-mode cluster --name wordCountTest ~/Spark-Demo-1.0-SNAPSHOT.jar hdfs:///data/data0916.txt
 * 基于standalone提交模式
 * spark-submit --master local --name wordCountTest ~/Spark-Demo-1.0-SNAPSHOT.jar hdfs:///data/data0916.txt
 * @author: hux
 * @create: 2020-09-16 14:19
 **/
public class SparkRunApp {
    public static void main(String[] args) throws IOException {
        System.setProperty("HADOOP_USER_NAME", "root");
        SparkConf conf = new SparkConf().setAppName("wordCountTest")
                .setMaster("spark://172.16.122.28:7077")
                .setJars(new String[]{"hdfs:///data/Spark-Demo-1.0-SNAPSHOT.jar"})
                .setIfMissing("spark.driver.host", "172.20.4.198");
//        SparkContext sparkContext = new SparkContext(conf);

        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        //使用JavaSparkContext创建RDD
        JavaRDD<String> lines = sparkContext.textFile("hdfs:///data/data0916.txt", 12);

        long numAs = lines.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("a");
            }
        }).count();
        long numBs = lines.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("b");
            }
        }).count();
        //转为key-value形式
        JavaPairRDD<String, Integer> pairRDD = lines.mapToPair(k -> new Tuple2<>(k, 1));
        List list1 = pairRDD.reduceByKey((x, y) -> x + y).collect();
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        list1.forEach(System.out::println);

    }
}
