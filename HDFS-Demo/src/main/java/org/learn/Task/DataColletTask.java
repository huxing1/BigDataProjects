package org.learn.Task;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.learn.Config.GlobalConstants;
import org.learn.Config.HDFSConn;
import org.learn.Utils.PropsHolder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;
import java.util.UUID;

/**
 * @program: BigDataProjects
 * @description:日志采集任务线程类
 * @author: hux
 * @create: 2020-05-27 15:28
 **/
public class DataColletTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("---------采集线程启动--------");
        try {
            //获取配置信息
            Properties properties = PropsHolder.getProperties();
            // 获取日志源存储目录中需要采集的文件<过滤掉正在追加数据的access.log文件>
            File srcDir = new File(properties.getProperty(GlobalConstants.LOG_SRC_PATH));
            File[] srcFiles = srcDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.startsWith(properties.getProperty(GlobalConstants.LOG_SRC_FILE_PREFIX))) {
                        return true;
                    }
                    return false;
                }
            });

            // 将需要采集的文件移动到一个临时目录，避免任务在执行过程中因为原文件修改导致异常
            File toUploadDir = new File(properties.getProperty(GlobalConstants.LOG_TOUPLOAD));
            for (File file : srcFiles) {
                FileUtils.moveFileToDirectory(file, toUploadDir, true);
            }

            // 获取待上传临时目录中的所有文件
            File[] toUploadFiles = toUploadDir.listFiles();

            // 获取当前的日期字符串
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-mm");
            String dayHourString = simpleDateFormat.format(date);
            String dayString = dayHourString.substring(0, dayHourString.lastIndexOf("-"));

            HDFSConn hdfsConn = new HDFSConn();
            FileSystem fs = hdfsConn.initHDFS();
            // 遍历待上传临时目录中的每一个文件
            for (File file : toUploadFiles) {
                // 生成目标文件名
                String dstName = properties.getProperty(GlobalConstants.LOG_HDFS_FILE_PREFIX) + UUID.randomUUID() + properties.getProperty(GlobalConstants.LOG_HDFS_FILE_SUFFIX);
                // 拼接hdfs上的完整的目标存储路径
                Path destPath = new Path(properties.getProperty(GlobalConstants.LOG_HDFS_BASE_PATH) + dayString + "/" + dstName);
                // 上传该文件
                System.out.println("准备上传：" + file.getPath());

                fs.copyFromLocalFile(new Path(file.getPath()), destPath);
                System.out.println("上传完毕");

                // 生成备份目录路径： 备份文件按小时分文件夹存放
                File backupDir = new File(properties.getProperty(GlobalConstants.LOG_BAK_BASE_PATH) + dayHourString);
                System.out.println("即将备份到：" + backupDir.getPath());

                // 移动到备份目录
                FileUtils.moveFileToDirectory(file, backupDir, true);
                System.out.println("备份完毕" + backupDir.getPath());
            }
            fs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }
}
