package priv.jesse.mall.service;


import priv.jesse.mall.entity.log.ShoppingLogs;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
public interface HBaseService {
    //把javabean中的信息 埋点到hbase集群
    void dataToHBase(ShoppingLogs shoppingLogs);
    //

}
