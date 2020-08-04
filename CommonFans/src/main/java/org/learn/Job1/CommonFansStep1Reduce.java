package org.learn.Job1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @program: BigDataProjects
 * @description:
 * @author: hux
 * @create: 2020-08-03 14:43
 **/
public class CommonFansStep1Reduce extends Reducer<Text,Text,Text,Text> {

    @Override
    protected void reduce(Text fan, Iterable<Text> users, Context context) throws IOException, InterruptedException {
        ArrayList<Text> userList = new ArrayList<>();
        for (Text user:users){
            userList.add(new Text(user));
        }
        Collections.sort(userList);

        for (int i=0;i<userList.size();i++){
            for (int j=i+1;j<userList.size();j++){
                context.write(new Text(userList.get(i)+"+"+userList.get(j)),fan);
            }
        }
    }
}
