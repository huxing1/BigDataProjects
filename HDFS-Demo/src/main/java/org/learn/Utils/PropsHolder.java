package org.learn.Utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @program: BigDataProjects
 * @description: 单例模式，双重检查锁 获取 Properties
 * @author: hux
 * @create: 2020-05-27 15:22
 **/
public class PropsHolder {
    private static Properties properties=null;
    public static Properties getProperties() throws IOException {
        if (properties==null){
            synchronized (PropsHolder.class){
                if (properties==null){
                    properties=new Properties();
                    properties.load(PropsHolder.class.getClassLoader().getResourceAsStream("datacollector.properties"));
                }
            }
        }
        return properties;
    }
}
