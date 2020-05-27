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
## 2.项目 HDFS-Demo
### 2.1实现功能
* hdfs api增删改查  
>对应的jar包见相关的pom文件，主要方法在org.learn.Utils的HDFSUtil.java
* 日志文件定时采集日志传输至HDFS
>主要方法在org.learn.Task的DataColletTask.java和DataBackUpCleanTask.java方法中，入口在org.learn DataCollectMainApp.java中

