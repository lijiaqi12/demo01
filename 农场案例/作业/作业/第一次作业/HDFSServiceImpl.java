package priv.jesse.mall.service.impl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.HDFSDao;
import priv.jesse.mall.service.HDFSService;
import priv.jesse.mall.utils.TimeUtils;

import java.io.IOException;
import java.util.Properties;

@Service
public class HDFSServiceImpl implements HDFSService {

    @Override
    public void logToHDFS(String line) {
        //实现具体逻辑
        FileSystem fileSystem=HDFSDao.getFileSystem();
        Properties properties=HDFSDao.getProperties();
        //操作
        try {
            String path=properties.getProperty("spring.hdfs.shoppingLog")+ TimeUtils.getHDFSTimes()+".log";
            //把输出流提出去
            FSDataOutputStream outputStream=null;
            //if判断
            if(fileSystem.exists(new Path(path))){
                outputStream=fileSystem.append(new Path(path));
            }else{
                //获取HDFS的输出
                outputStream=fileSystem.create(new Path(path));
            }
            Configuration conf=new Configuration();
            conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
            conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
            IOUtils.copyBytes(org.apache.commons.io.IOUtils.toInputStream(line+"\r\n"),outputStream,conf,true);
            /*
            outputStream.writeUTF(line);
            outputStream.hflush();
             */
            //关流
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
