package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.HbaseDao;
import priv.jesse.mall.entity.log.ShoppingLogs;
import priv.jesse.mall.service.HBaseService;

import java.util.Date;

@Service
public class HBaseServiceImpl implements HBaseService {

    @Autowired
    HbaseDao hbaseDao;

    @Override
    public void dataToHBase(ShoppingLogs shoppingLogs) {
        //设计rowkey 手机号+时间戳
        String rowkey=shoppingLogs.getPhoneNumber()+"-"+new Date().getTime();
        hbaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","name", shoppingLogs.getName());
        hbaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","phoneNumber", shoppingLogs.getPhoneNumber());
        hbaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","productId", String.valueOf(shoppingLogs.getProductId()));
        hbaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","title", shoppingLogs.getTitle());
        hbaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","shopPrice", String.valueOf(shoppingLogs.getShopPrice()));
        hbaseDao.addAndUpdate("shoppingLogs",rowkey,"shoplog","times", shoppingLogs.getTimes());
    }
}
