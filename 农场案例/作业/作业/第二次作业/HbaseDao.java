package priv.jesse.mall.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;
import priv.jesse.mall.entity.log.ShoppingLogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Service
public class HbaseDao {
    static Connection connection;

    static {
        //获取hbase配置文件
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "192.168.221.128");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        try {
            //
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    增加或者删除 put
     */
    public void addAndUpdate(String tablename, String rowkey, String columFamily, String colum, String value) {
        try {
            Table table = connection.getTable(TableName.valueOf(tablename));
            //创建put
            Put put = new Put(Bytes.toBytes(rowkey));
            //给put添加值
            put.addColumn(Bytes.toBytes(columFamily), Bytes.toBytes(colum), Bytes.toBytes(value));
            //添加数据
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delectDate(String tableName, String rowkey) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //创建delete
            Delete delete = new Delete(Bytes.toBytes(rowkey));
            //删除
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询一条数据 返回值肯定是javabean
     */
    public ShoppingLogs getOne(String tableName, String rowkey) {
        ShoppingLogs shoppingLogs = new ShoppingLogs();
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //创建get
            Get get = new Get(Bytes.toBytes(rowkey));
            //获取一条数据的数据集
            Result result = table.get(get);
            //取出数据集的数据
            Cell[] cells = result.rawCells();
            //循环遍历 获取每一个值
            for (Cell cell : cells) {
                String key = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                if (key.equals("name")) {
                    shoppingLogs.setName(value);
                } else if (key.equals("phoneNumber")) {
                    shoppingLogs.setPhoneNumber(value);
                } else if (key.equals("productId")) {
                    shoppingLogs.setProductId(Integer.parseInt(value));
                } else if (key.equals("title")) {
                    shoppingLogs.setTitle(value);
                } else if (key.equals("shopPrice")) {
                    shoppingLogs.setShopPrice(Double.parseDouble(value));
                } else {
                    shoppingLogs.setTimes(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shoppingLogs;
    }

    /**
     * 查询多条数据
     */
    public List<ShoppingLogs> getAll(String tableName) {
        ArrayList<ShoppingLogs> shoppingList = new ArrayList<>();
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //获取所有数据
            ResultScanner resultScanner = table.getScanner(new Scan());
            //先获取到每一行数据 rowkey
            for (Result result : resultScanner) {
                //每一个rowkey的数据我们就得封装成一个实体类
                ShoppingLogs shoppingLogs = new ShoppingLogs();
                //获取每一个数据集
                Cell[] cells = result.rawCells();
                //循环遍历
                for (Cell cell : cells) {
                    String key = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String value = Bytes.toString(CellUtil.cloneValue(cell));
                    if (key.equals("name")) {
                        shoppingLogs.setName(value);
                    } else if (key.equals("phoneNumber")) {
                        shoppingLogs.setPhoneNumber(value);
                    } else if (key.equals("productId")) {
                        shoppingLogs.setProductId(Integer.parseInt(value));
                    } else if (key.equals("title")) {
                        shoppingLogs.setTitle(value);
                    } else if (key.equals("shopPrice")) {
                        shoppingLogs.setShopPrice(Double.parseDouble(value));
                    } else {
                        shoppingLogs.setTimes(value);
                    }
                }
                //添加到list集合中
                shoppingList.add(shoppingLogs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shoppingList;
    }
}
