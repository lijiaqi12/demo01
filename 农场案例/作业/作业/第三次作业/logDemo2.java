package com.group.one.logNeed;

import com.group.one.utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logDemo2 {

    /**
     * 需求2 统计山东省（province）售卖蛤蜊(name)的农产品市场(market)
     * 占全省农产品市场的比例
     */
    public static void logNeed2(){
        Connection hive_conn= ConnectUtils.getHive_conn();
        Connection mysql_conn=ConnectUtils.getMysql_conn();
        //写代码
        PreparedStatement hive_ps= null;
        try {
            hive_ps = hive_conn.prepareStatement("select round(b.counts/a.sums,2) from\n" +
                    "(select count(distinct market)sums from t_farm1 where province=\"山东\")a,\n" +
                    "(select count(distinct market)counts from t_farm1 where province=\"山东\" \n" +
                    "and name=\"蛤蜊\")b");
            //获取查询结果
            ResultSet resultSet=hive_ps.executeQuery();
            while (resultSet.next()){
//                System.out.println(resultSet.getString(1));
//                System.out.println(resultSet.getInt(2));
                //把处理的结果写入到mysql
                PreparedStatement mysql_ps=mysql_conn.prepareStatement("insert into shandongAndgeli values(?)");
                //赋值
                mysql_ps.setFloat(1, resultSet.getFloat(1));
                //提交
                mysql_ps.executeUpdate();
            }

            //关流
            ConnectUtils.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        logNeed2();
    }
}
