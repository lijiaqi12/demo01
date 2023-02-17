package priv.jesse.mall.service;


/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
public interface HDFSService {
    /**
     * 把埋点的json数据写入hdfs
     */
     void logToHDFS(String line);


}
