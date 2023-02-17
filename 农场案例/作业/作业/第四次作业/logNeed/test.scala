package logNeed

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}


object test {
  Logger.getLogger("org").setLevel(Level.WARN)
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().set("dfs.client.use.datanode.hostname","true")
    //sparkSeesion   本地测试     spark替代的是mapReduce
    val session = SparkSession.builder().appName("logDemo1").
      master("local[*]")
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()

    //读取数据    全路径名字    192.168.70.110:50070     sparkCore:RDD
    //frame  DataFrame:sparkSql
    //    val frame: DataFrame = session.read.json("hdfs://iZ2zei01n7f2wr6wef5vfmZ:9000/logs/shoppingLog/2022-05-19.log")
    //    frame.show()
    session.read.json("hdfs://iZ2zei01n7f2wr6wef5vfmZ:9000/logs/shoppingLog/2022-05-19.log").createTempView("shoplog1")
    session.read.json("hdfs://iZ2zei01n7f2wr6wef5vfmZ:9000/logs/shoppingLog/2022-05-20.log").createTempView("shoplog2")
    session.read.json("hdfs://iZ2zei01n7f2wr6wef5vfmZ:9000/logs/cartLog/2022-05-19.log").createTempView("cartlog1")
    session.read.json("hdfs://iZ2zei01n7f2wr6wef5vfmZ:9000/logs/cartLog/2022-05-20.log").createTempView("cartlog2")
    session.read.json("hdfs://iZ2zei01n7f2wr6wef5vfmZ:9000/logs/payLog/2022-05-19.log").createTempView("paylog1")
    session.read.json("hdfs://iZ2zei01n7f2wr6wef5vfmZ:9000/logs/payLog/2022-05-20.log").createTempView("paylog2")

    //    //准备写sql    第一步：创建表
    //    frame.createTempView("shoplog")
    //                    购物车
    //                   Shoplog |name|phoneNumber|productId|shopPrice|     times|    title|
    //                          |  we|12122222222|        5|       66|2022-05-19 16:44:43| 漂亮字速成36记|
    //                  取消收藏
    //                  CartLog  |name|productId|shopPrice|              times|        title|
    //                           |zdf2|        4|       20|2022-05-19 21:45:17| 二战德军十大将帅|
    //                 支付
    //                 PayLog  |      addr| id|  name|      phone|state|              times|total|
    //                         |      jxnu| 32|  zdf3|12345678900|    2|2022-05-19 22:50:34|   46|
    //统计二战德军十大将帅 在所有加入购物车所占的比例
    //    session.sql(
    //      """
    //        |select * from(
    //        |select a.title,a.count(*)counts1
    //        |from (
    //        |select * from shoplog1
    //        |union all
    //        |select * from shoplog2
    //        |)a
    //        |where a.title="二战德军十大将帅")
    //        |""".stripMargin).show()
    session.sql(
      """
        |select title,count(*)counts1
        |from (
        |select * from shoplog1
        |union all
        |select * from shoplog2
        |)
        |where title="二战德军十大将帅"
        |group by title
        |""".stripMargin).show()
    session.sql(
      """
        |select count(*)counts2
        |from (
        |select * from shoplog1
        |union all
        |select * from shoplog2
        |)
        |""".stripMargin).show()

    session.sql(
      """
        |select round(a.counts1/b.counts2,2)bli from
        |(select count(*)counts1
        |from (
        |select * from shoplog1
        |union all
        |select * from shoplog2)
        |where title="二战德军十大将帅"
        |group by title )a,
        |(select count(*)counts2
        |from (
        |select * from shoplog1
        |union all
        |select * from shoplog2 )
        |)b
        |""".stripMargin).show()
    //     session.sql(
    //       """
    //         |select round(b.counts2/a.counts1)
    //         |from
    //         |(select count(*)counts1 from (
    //         |select * from shoplog1
    //         |union all
    //         |select * from shoplog2))a,
    //         |(select count(*)counts2 from shoplog1
    //         |union all
    //         |select * from shoplog2
    //         |)
    //         |where title="二战德军十大将帅"
    //         |group by title)b;
    //         |""".stripMargin).show()

  }

}
