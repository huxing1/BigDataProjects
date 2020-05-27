package org.learn;

import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.learn.Config.HDFSConn;
import org.learn.Utils.HDFSUtil;

import java.io.IOException;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-05-26 16:03
 **/
public class HDFSUtilsTest {

    @Test
    public void connFSTest() throws IOException {
        HDFSConn hdfsConn = new HDFSConn();
        Path path = new Path("/input");
        System.out.println( hdfsConn.initHDFS().exists(path));
    }

    @Test
    public void writeFSTest() throws IOException {
        HDFSUtil hdfsUtil=new HDFSUtil();
        hdfsUtil.writeFS("/input/test0527","hello world");
        System.out.println("ok");
    }

    @Test
    public void readFSTest() throws IOException {
        HDFSUtil hdfsUtil=new HDFSUtil();
        System.out.println(hdfsUtil.readFS("/access-log/2020-05-27/access_4e714874-0fbc-48b5-9a42-8c38ea74fc89_.log"));
    }

    @Test
    public void fileCopyWithProgressTest() throws IOException {
       HDFSUtil hdfsUtil=new HDFSUtil();
       hdfsUtil.fileCopyWithProgress("test0527","/input/test0527");
    }

    @Test
    public void deleteFSTest() throws IOException {
        HDFSUtil hdfsUtil=new HDFSUtil();
        hdfsUtil.deleteFS("/access-log/",true);
    }
}
