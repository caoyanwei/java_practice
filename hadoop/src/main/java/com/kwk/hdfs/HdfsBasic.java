package com.kwk.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.IOException;


public class HdfsBasic {

    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "caoyanwei");
//        testCreate();
        testGetHostName();
    }

    private static void testGetHostName() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.11.130:9000");
        conf.set("dfs.permission","false");

        DistributedFileSystem hdfs = (DistributedFileSystem) FileSystem.get(conf);
        DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();

        for (DatanodeInfo dataNode : dataNodeStats) {
            System.out.println(dataNode.getHostName() + "\t" + dataNode.getName());
        }
    }

    public static void testCreate() throws Exception {
        String hdfsPath = "hdfs://192.168.11.130:9000/cyw";

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.11.130:9000");
        conf.set("dfs.permission","false");

        byte[] buff = "hello world!".getBytes();

        FileSystem hdfs = FileSystem.get(conf);
        Path dst = new Path(hdfsPath + "/hello.txt");
        FSDataOutputStream outputStream = null;
        try {
            outputStream = hdfs.create(dst);
            outputStream.write(buff, 0, buff.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

        FileStatus files[] = hdfs.listStatus(dst);
        for (FileStatus file : files) {
            System.out.println(file.getPath());
        }
    }
}
