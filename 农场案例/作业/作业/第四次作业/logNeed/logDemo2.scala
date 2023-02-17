package logNeed

import org.apache.spark.sql.{SaveMode, SparkSession}

import java.util.Properties

object logDemo2 {
  def main(args: Array[String]): Unit = {
    val session=SparkSession.builder().appName("logDemo2").
      master("local[*]").config("spark.sql.crossJoin.enabled","true").getOrCreate()

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
        |select round(a.sums/b.counts,2)percentages from
        |(select count(title)sums from
        |(select * from shoplog1
        |union all
        |select * from shoplog2) where title="安踏篮球鞋")a,
        |(select count(title)counts from
        |(select * from shoplog1
        |union all
        |select * from shoplog2))b
        |""".stripMargin).show()
    //把我们处理分析的结果 入库到mysql
    //.write.mode(SaveMode.Append).jdbc("jdbc:mysql://192.168.221.128:3306/shopLog?characterEncoding=utf-8&useSSL=false","percentage",properties)
  }
}

