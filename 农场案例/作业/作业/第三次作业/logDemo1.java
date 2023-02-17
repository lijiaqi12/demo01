package com.group.one.logNeed;

import com.group.one.utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logDemo1 {
    /**
     * 需求1 统计每个省份(province)的农产品市场(market)总数
     */
    public static void logNeed1(){
        Connection hive_conn= ConnectUtils.getHive_conn();
        Connection mysql_conn=ConnectUtils.getMysql_conn();
        //写代码
        PreparedStatement hive_ps= null;
        try {
            hive_ps = hive_conn.prepareStatement("select province,count(distinct market)counts from \n" +
                    "t_farm1 group by province \n" +
                    "having province <> \"\"");
            //获取查询结果
            ResultSet resultSet=hive_ps.executeQuery();
            while (resultSet.next()){
//                System.out.println(resultSet.getString(1));
//                System.out.println(resultSet.getInt(2));
                //把处理的结果写入到mysql
                PreparedStatement mysql_ps=mysql_conn.prepareStatement("insert into provinceMarket values(?,?)");
                //赋值
                mysql_ps.setString(1, resultSet.getString(1));
                mysql_ps.setInt(2, resultSet.getInt(2));
                //提交
                mysql_ps.executeUpdate();
            }

            //关流
            ConnectUtils.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        logNeed1();
    }
}
