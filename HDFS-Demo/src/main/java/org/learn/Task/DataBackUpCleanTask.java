package org.learn.Task;

import org.apache.commons.io.FileUtils;
import org.learn.Config.GlobalConstants;
import org.learn.Utils.PropsHolder;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;

/**
 * @program: BigDataProjects
 * @description: 备份日志清理
 * @author: hux
 * @create: 2020-05-27 16:02
 **/
public class DataBackUpCleanTask extends TimerTask {
    @Override
    public void run() {
        Date date = new Date();
        System.out.println("备份处理线程启动，当前时间："+date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");

        try {
            Properties properties = PropsHolder.getProperties();
            // 获取一级备份目录
            File backupBaseDir = new File(properties.getProperty(GlobalConstants.LOG_BAK_BASE_PATH));
            // 遍历所有二级子目录
            File[] backupDirs = backupBaseDir.listFiles();
            for (File dir:backupDirs){
                Date backDate = simpleDateFormat.parse(dir.getName());
                // 计算子备份目录的备份时间是否已超24小时
                if (date.getTime()-backDate.getTime()>24*60*60*1000L){
                    // 如果超出24小时，则删除
                    System.out.println("检测到需要清除的备份文件夹"+dir.getPath());
                    FileUtils.deleteQuietly(dir);
                    System.out.println("成功删除");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
