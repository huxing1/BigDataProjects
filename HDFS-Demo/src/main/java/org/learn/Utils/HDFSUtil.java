package org.learn.Utils;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.learn.Config.HDFSConn;

import java.io.*;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-05-26 15:50
 **/
public class HDFSUtil {

    /**
    * @Description: 创建HDFS文件并写入
    * @Param: [file, value]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/26
    */
    public void writeFS(String file,String value) throws IOException {
        HDFSConn hdfsConn=new HDFSConn();
        FileSystem fileSystem=hdfsConn.initHDFS();
        Path path = new Path(file);
        FSDataOutputStream fsDataOutputStream = fileSystem.create(path);
        fsDataOutputStream.writeUTF(value);
        fsDataOutputStream.close();
    }

    /**
    * @Description: 读取HDFS文件并写入
     * 注意这里不要用readUTF方法，因为很容易报EOFException的错误
    * @Param: [file]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/26
    */
    public String readFS(String file) throws IOException {
        HDFSConn hdfsConn=new HDFSConn();
        FileSystem fileSystem=hdfsConn.initHDFS();
        Path remotePath = new Path(file);
        FSDataInputStream inputStream = fileSystem.open(remotePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line + "\n");
        }
        fileSystem.close();
        return buffer.toString();
    }

    /**
    * @Description: 删除HDFS
    * @Param: [file, recursive]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/27
    */
    public void deleteFS(String file,boolean recursive) throws IOException {
        HDFSConn hdfsConn=new HDFSConn();
        FileSystem fileSystem=hdfsConn.initHDFS();
        Path path = new Path(file);
        boolean result = fileSystem.delete(path, recursive);
        if (result){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        fileSystem.close();
    }


    /**
    * @Description: 上传文件到HDFS，并打印进度条
    * @Param: [localSrc, fsSrc]
    * @return: void
    * @Author: hux
    * @Date: 2020/5/27
    */
    public void fileCopyWithProgress(String localSrc,String fsSrc) throws IOException {
        HDFSConn hdfsConn=new HDFSConn();
        FileSystem fileSystem=hdfsConn.initHDFS();
        InputStream inputStream = new BufferedInputStream(new FileInputStream(localSrc));
        OutputStream outputStream = fileSystem.create(new Path(fsSrc), new Progressable() {
            @Override
            public void progress() {
                System.out.print(".");
            }
        });
        IOUtils.copyBytes(inputStream,outputStream,4096,true);
    }


}
