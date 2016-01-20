package com.kwk.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HdfsBasic {

    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "caoyanwei");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.11.130:9000");
        conf.set("dfs.permission", "false");
        conf.setBoolean("dfs.support.append", true);
//        testCreate(conf);
        testRead(conf);
//        testGetHostName(conf);
    }

    private static void testRead(Configuration conf) throws IOException {
        String hdfsPath = "hdfs://192.168.11.130:9000/cyw";
        Path dst = new Path(hdfsPath + "/hello.txt");

        FileSystem hdfs = FileSystem.get(conf);
        FSDataInputStream in = null;
        try {
            in = hdfs.open(dst);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    //还有问题
    private static void testAppend(Configuration conf) throws IOException {
        String hdfsPath = "hdfs://192.168.11.130:9000/cyw";

        byte[] buff = "hello world!\n".getBytes();
        FileSystem hdfs = FileSystem.get(conf);
        Path dst = new Path(hdfsPath + "/hello.txt");
        FSDataOutputStream outputStream = null;
        try {
            outputStream = hdfs.append(dst);
            outputStream.write(buff, 0, buff.length);
            outputStream.hflush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private static void testGetHostName(Configuration conf) throws IOException {
        DistributedFileSystem hdfs = (DistributedFileSystem) FileSystem.get(conf);
        DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();

        for (DatanodeInfo dataNode : dataNodeStats) {
            System.out.println(dataNode.getHostName() + "\t" + dataNode.getName());
        }
    }

    public static void testCreate(Configuration conf) throws Exception {
        String hdfsPath = "hdfs://192.168.11.130:9000/cyw";

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
