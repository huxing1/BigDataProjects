package api

import org.apache.spark.{SparkConf, SparkContext}

object SparkRunTest {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME","root")
    val conf = new SparkConf()
//      .setAppName("yarn")
//      .setMaster("spark://test1:7077")
//      .setJars(Array("hdfs:///data/Spark-Scala-Demo-1.0-SNAPSHOT.jar"))
//      .set("spark.driver.host", "172.20.4.198");
    val sc = new SparkContext(conf)
    //    val path=this.getClass.getClassLoader.getResource("data.txt").getPath
    //    val file= Source.fromFile("D:\\文档书籍代码\\coding\\A-learn\\BigDataProjects\\Spark-Demo\\src\\main\\resources\\data.txt","UTF-8").getLines().toList

    //    println(file.getPath)
    //    println(file.getContent.toString)
    //    val readmeText : Iterator[String] = Source.fromResource("data.txt").getLines
    //    while(readm
    //    eadmeText.next())
    //    }

    val lines = sc.textFile("hdfs:///data/data0916.txt")

    val counts = lines.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    val list=counts.collect()
    list.foreach(println)
//    counts.saveAsTextFile("/root/output/")
  }
}
