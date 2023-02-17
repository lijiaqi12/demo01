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

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
@Service
public class HBaseDao {
    static Connection connection;
    //获取hbase的连接对象
    static {
        //创建hbase配置文件
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "192.168.70.110");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        try {
            //hbase的连接对象
             connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加或者修改   put
     */
    public void addAndUpdate(String tableName,String rowKey,String columFamily,String colum,String value ){
            //获取操作表内容的对象
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //创建Put
            Put put = new Put(Bytes.toBytes(rowKey));
            //给put添加值  byte [] family, byte [] qualifier, byte [] value
            put.addColumn(Bytes.toBytes(columFamily),Bytes.toBytes(colum),Bytes.toBytes(value));
            //添加数据
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据    删除一条
     */
    public void deleteDate(String tableName,String rowKey){
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //创建Delete
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            //删除
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询一条数据    返回值肯定是javabean
     */
    public ShoppingLogs getOne(String tableName,String rowKey){
        //String name, String phoneNumber, int productId, String title,
        // Double shopPrice, String times
        ShoppingLogs shoppingLogs = new ShoppingLogs();
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //创建get
            Get get = new Get(Bytes.toBytes(rowKey));
            //获取一条数据的结果集
            Result result = table.get(get);
            //取出结果集的数据
            Cell[] cells = result.rawCells();
            //循环遍历  获取每一个值
            for (Cell cell : cells) {
                //name   phoneNumber.....
                String key = Bytes.toString(CellUtil.cloneQualifier(cell));
                //具体的值
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                //赋值
                if(key.equals("name")){
                    shoppingLogs.setName(value);
                }else if(key.equals("phoneNumber")){
                    shoppingLogs.setPhoneNumber(value);
                }else if(key.equals("productId")){
                    shoppingLogs.setProductId(Integer.parseInt(value));
                }else if(key.equals("title")){
                    shoppingLogs.setTitle(value);
                }else if(key.equals("shopPrice")){
                    shoppingLogs.setShopPrice(Double.parseDouble(value));
                }else{
                    shoppingLogs.setTimes(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return shoppingLogs;
    }

    /**
     * 查询多条数据    List<javabean>
     */
    public List<ShoppingLogs> getAll(String tableName){
        ArrayList<ShoppingLogs> shoppingList = new ArrayList<>();
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //获取所有数据
            ResultScanner resultScanner = table.getScanner(new Scan());
            //先获取到每一行数据  rowKey
            for (Result result : resultScanner) {
                //每一个rowkey的数据 我们就得封装成一个实体类
                ShoppingLogs shoppingLogs = new ShoppingLogs();
                //获取每一个数据集
                Cell[] cells = result.rawCells();
                //循环遍历
                for (Cell cell : cells) {
                    //name   phoneNumber.....
                    String key = Bytes.toString(CellUtil.cloneQualifier(cell));
                    //具体的值
                    String value = Bytes.toString(CellUtil.cloneValue(cell));
                    //赋值
                    if(key.equals("name")){
                        shoppingLogs.setName(value);
                    }else if(key.equals("phoneNumber")){
                        shoppingLogs.setPhoneNumber(value);
                    }else if(key.equals("productId")){
                        shoppingLogs.setProductId(Integer.parseInt(value));
                    }else if(key.equals("title")){
                        shoppingLogs.setTitle(value);
                    }else if(key.equals("shopPrice")){
                        shoppingLogs.setShopPrice(Double.parseDouble(value));
                    }else{
                        shoppingLogs.setTimes(value);
                    }
                }
                //添加到list集合总
                shoppingList.add(shoppingLogs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shoppingList;
    }

}
