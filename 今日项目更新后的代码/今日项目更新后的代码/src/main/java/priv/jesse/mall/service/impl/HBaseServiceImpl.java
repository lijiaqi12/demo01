package priv.jesse.mall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.HBaseDao;
import priv.jesse.mall.entity.log.ShoppingLogs;
import priv.jesse.mall.service.HBaseService;

import java.util.Date;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
@Service
public class HBaseServiceImpl implements HBaseService {

    @Autowired
    HBaseDao hBaseDao;


    @Override
    public void dataToHBase(ShoppingLogs shoppingLogs) {
        //String name, String phoneNumber, int productId, String title, Double shopPrice, String times
       // "shoppingLogs","shoplog"
     //String tableName,String rowKey,String columFamily,String colum,String value
        String rowkey=shoppingLogs.getPhoneNumber()+"-"+new Date().getTime();
        //设计rowkey    手机号+时间戳
        hBaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","name",shoppingLogs.getName());
        hBaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","phoneNumber",shoppingLogs.getPhoneNumber());
        hBaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","productId",String.valueOf(shoppingLogs.getProductId()));
        hBaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","title",shoppingLogs.getTitle());
        hBaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","shopPrice",String.valueOf(shoppingLogs.getShopPrice()));
        hBaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","times",shoppingLogs.getTimes());
    }
}
