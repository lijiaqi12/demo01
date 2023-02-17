package logNeed

import org.apache.spark.sql.{SaveMode, SparkSession}

import java.util.Properties

object logDemo5 {
  def main(args: Array[String]): Unit = {
    //统计电话号码为17511683035的用户的名字以及这五天分别购买的物品
    val session=SparkSession.builder().appName("logDemo5").
      master("local[*]").getOrCreate()

    //读取数据  全路径名字
    //frame DataFrame:sparksql
    val frame1=session.read.json("hdfs://192.168.221.128:8020/logs/shoppingLog/2022-05-18.log");
    //输出
    //frame.show();
    //准备写sql  第一步：创建表
    frame1.createTempView("shoplog1")
        val frame2=session.read.json("hdfs://192.168.221.128:8020/logs/shoppingLog/2022-05-19.log");
        frame2.createTempView("shoplog2")
    val properties=new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","123456")
    //写sql语句 user.getName(),user.getPhone(),product.getId(),product.getTitle(),product.getShopPrice(), TimeUtils.getTimes()
    //name|phoneNumber|productId|shopPrice|              times|               title
    session.sql(
      """
        |select * from
        |(select shoplog.times,shoplog.name,shoplog.title
        |from
        |(select * from shoplog1
        |union all
        |select * from shoplog2)shoplog
        |where shoplog.phoneNumber="17511683035")
        |""".stripMargin)
    //把我们处理分析的结果 入库到mysql
    .write.mode(SaveMode.Append).jdbc("jdbc:mysql://192.168.221.128:3306/shopLog?characterEncoding=utf-8&useSSL=false","phoneNumber",properties)
  }
}


//select t_farm.province,t_farm.times,t_farm.name,
//round(if(count(*)>2,
//(sum(price)-max(price)-min(price))/(count(*)-2),
//sum(price)/count(*)),2)as avgsss
//from
//(select *from t_farm1 where province="山西"
//union all
//select *from t_farm2 where province="山西"
//union all
//select *from t_farm3 where province="山西"
//union all
//select *from t_farm4 where province="山西"
//union all
//select *from t_farm5 where province="山西"
//) t_farm
//group by t_farm.province,t_farm.times,t_farm.name;