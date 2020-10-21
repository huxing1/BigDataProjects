package api


import org.apache.spark.{SparkConf, SparkContext}
import scala.io.Source

object CountLine {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordCountTest").setMaster("spark://test1:7077")
      .setJars(Array("Spark-Demo/target/Spark-Demo-1.0-SNAPSHOT.jar"))
      .set("spark.driver.host", "172.20.4.198")
    val sc = new SparkContext(conf)
//    val path=this.getClass.getClassLoader.getResource("data.txt").getPath
//    val file= Source.fromFile("D:\\文档书籍代码\\coding\\A-learn\\BigDataProjects\\Spark-Demo\\src\\main\\resources\\data.txt","UTF-8").getLines().toList

//    println(file.getPath)
//    println(file.getContent.toString)
//    val readmeText : Iterator[String] = Source.fromResource("data.txt").getLines
//    while(readmeText.hasNext){
//      println(readmeText.next())
//    }

    val lines = sc.textFile("hdfs:///data/data0916.txt")

    val lineLengths = lines.map(s => s.length)
    val totalLength = lineLengths.reduce((a, b) => a + b)
    println("ok")
  }
}
