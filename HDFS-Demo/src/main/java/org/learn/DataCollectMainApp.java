package org.learn;

import org.learn.Task.DataBackUpCleanTask;
import org.learn.Task.DataColletTask;

import java.util.Timer;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-05-27 16:09
 **/
public class DataCollectMainApp {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new DataColletTask(),0,60*1000L);
        timer.schedule(new DataBackUpCleanTask(),0,60*60*1000L);
    }
}
