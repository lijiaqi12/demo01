package priv.jesse.mall.dao;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
public class HDFSDao {
    //hdfs的API
    static FileSystem fileSystem;
   static Properties properties;
    static {
        //properties
         properties = new Properties();
        //加载配置文件
        try {
            properties.load(HDFSDao.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration conf = new Configuration();
        conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
        try {
             fileSystem = FileSystem.get(URI.create(properties.getProperty("spring.hdfs.url")), conf, "root");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对外提供一个获取Properties的方法
     */
    public static Properties getProperties(){
        return properties;
    }

    /**
     * 对外提供一个获取fileSystem的方法
     */
    public static FileSystem getFileSystem(){
        return fileSystem;
    }
}
