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

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
@Service
public class HDFSServiceImpl implements HDFSService {

    /**
     * 把json数据埋到hdfs
     * @param line
     */
    @Override
    public  void logToHDFS(String line) {
        //实现具体逻辑
        FileSystem fileSystem = HDFSDao.getFileSystem();
        Properties properties = HDFSDao.getProperties();
        //操作   数据==》HDFS
        try {
//            //获取hdfs的输出流     内容再次写入被覆盖
            String path=properties.getProperty("spring.hdfs.shoppingLog")+TimeUtils.getHDFSTimes()+".log";
            //把输出流提出去
            FSDataOutputStream outputStream=null;
            //if判断
            if(fileSystem.exists(new Path(path))){
                //存在
                 outputStream = fileSystem.append(new Path(path));
            }else{
               // 不存在
                 outputStream = fileSystem.create(new Path(path));
            }
            //appen   InputStream in, OutputStream out, Configuration conf, boolean close
            Configuration configuration = new Configuration();
            configuration.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
            configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
            //把一个String类型变成inputStream
            IOUtils.copyBytes(org.apache.commons.io.IOUtils.toInputStream(line+"\r\n"),outputStream,configuration,true);
            //关流
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
