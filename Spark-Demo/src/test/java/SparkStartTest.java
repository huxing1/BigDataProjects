import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.junit.Test;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-09-16 11:59
 **/
public class SparkStartTest {
    @Test
    public void test() {
        SparkConf conf = new SparkConf().setAppName("wordCountTest").setMaster("spark://test1:7077")
                .setJars(new String[]{"target/Spark-Demo-1.0-SNAPSHOT.jar"})
                .setIfMissing("spark.driver.host", "172.20.4.198");
        SparkContext sparkContext = new SparkContext(conf);
        System.out.println("ok");

    }
}
