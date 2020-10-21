package api;

import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description: 参考：https://cloud.tencent.com/developer/article/1076246
 * @author: hux
 * @create: 2020-09-22 14:18
 **/
public class SparkHandle {
    private void handle() throws IOException {
        SparkAppHandle handler = new SparkLauncher()
                .setAppName("wordCountTest")
                .setSparkHome("/usr/local/software/spark-2.4.7")
                .setMaster("yarn")
                .setConf("spark.driver.memory", "2g")
                .setConf("spark.executor.memory", "1g")
                .setConf("spark.executor.cores", "3")
                .setAppResource("")
                .setMainClass("")
                .setDeployMode("cluster").startApplication(new SparkAppHandle.Listener() {
                    @Override
                    public void stateChanged(SparkAppHandle sparkAppHandle) {
                        System.out.println("**********  state  changed  **********");
                    }

                    @Override
                    public void infoChanged(SparkAppHandle sparkAppHandle) {
                        System.out.println("**********  info  changed  **********");
                    }
                });
    }
}
