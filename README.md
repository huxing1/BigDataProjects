# BigDataModule
## 1.项目环境配置
>hadoop-3.2.1  
>hbase-2.2.4  
>jdk1.8.0  
>zookeeper3.5.8  

环境配置文档参考--《...后续补充》 
## 2.项目 hbase-demo
### 2.1实现功能
* hbase api增删改查  
>对应的hbase-client jar包的版本可以在hbase的lib目录下找到，比如我的： hbase-client-2.2.4.jar
* 
## 3.项目 HDFS-Demo
### 3.1实现功能
* hdfs api增删改查  
>对应的jar包见相关的pom文件，主要方法在org.learn.Utils的HDFSUtil.java
* 日志文件定时采集日志传输至HDFS
>主要方法在org.learn.Task的DataColletTask.java和DataBackUpCleanTask.java方法中，入口在org.learn DataCollectMainApp.java中
## 4.项目 MapReduce-Demo
### 4.1实现功能
* MapReduce 实验 - 计算气温 最大/最小/平均 值
美国国家气候数据中心-气象数据集下载地址：ftp://ftp.ncdc.noaa.gov/pub/data/noaa 
#### 步骤

##### 1、 下载数据

由于全部数据非常庞大，这里只下载2017年的部分数据用于实验：

```
wget ftp://ftp.ncdc.noaa.gov/pub/data/noaa/2017 -r
```

最正确的数据格式请参考网站

![数据格式](http://image.laijianfeng.org/20181126_162421.png)

##### 2、 合并数据集

由于数据集是由非常多的小文件组成，通常情况下Hadoop处理少量的大型文件更容易、更有效，所以我们将用于实验的数据文件拼接成一个大文件


```
zcat *.gz > coaa.sample.txt
```

##### 3、 上传数据集到HDFS上
```
hadoop dfs -mkdir -p /hadoop/ch2
hadoop dfs -copyFromLocal coaa.sample.txt /hadoop/ch2
```
##### 4、 编写MapReduce程序
##### 5、 打包上传并运行作业
```
hadoop jar MapReduce-Demo-1.0-SNAPSHOT.jar Api.Reducer.MinTemperature /hadoop/weatherData0728 /output/weatherdata/min
```

也可以跨平台在本地运行
##### 6、 查看运行结果
```
hadoop dfs -cat /output/weatherdata/min/part-r-00000
```
##### 7、 查看作业状态
http://ip:8088/cluster

#### 补充
##### 设置 Combiner 可减少数据的传输量、提高效率
```
job.setCombinerClass(MaxTemperatureReducer.class);
```
##### 关于 Hadoop集群加入新节点

* 当Hadoop集群有新节点加入时，正在运行的MapReduce作业自动识别并使用新节点；
* 当新节点加入Hadoop集群后，再启动MapReduce作业，MapReduce作业也能自动识别并使用新节点