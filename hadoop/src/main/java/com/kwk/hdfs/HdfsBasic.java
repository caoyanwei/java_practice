package com.kwk.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;


public class HdfsBasic {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        Path inputDir = new Path("E:/KwDownload");
        String serverPath = "hdfs://192.168.146.128:9000/test";
        Path hdfsfile = new Path(serverPath);
        FileSystem hdfs = FileSystem.get(URI.create(serverPath), conf);

        FileSystem local = FileSystem.getLocal(conf);
        FileStatus[] status = local.listStatus(inputDir);
        FSDataOutputStream out = hdfs.create(hdfsfile);

        for(int i = 0; i < status.length; i++) {
            FSDataInputStream in = local.open(status[i].getPath());
            byte buffer[] = new byte[256];
            int byteread = 0;
            while((byteread = in.read(buffer)) > 0) {
                out.write(buffer);
            }
            in.close();
        }
        out.close();
    }
}
