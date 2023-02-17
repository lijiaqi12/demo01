package com.group.one.logNeed;

import com.group.one.utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logDemo4 {
    /**
     * 需求4 统计排名前3的省份（province）共同拥有的农产品(name)类型
     * 先把这三个省份的所有菜品去重
     */
    public static void logNeed4(){
        Connection hive_conn= ConnectUtils.getHive_conn();
        Connection mysql_conn=ConnectUtils.getMysql_conn();
        //写代码
        PreparedStatement hive_ps= null;
        try {
            hive_ps = hive_conn.prepareStatement("select c.name from \n" +
                    "(select a.name as aaa from\n" +
                    "(select distinct name from t_farm1 where province=\"北京\")a\n" +
                    "join\n" +
                    "(select distinct name from t_farm1 where province=\"江苏\")b\n" +
                    "on a.name=b.name)d join\n" +
                    "(select distinct name from t_farm1 where province=\"山东\")c\n" +
                    "on d.aaa=c.name");
            //获取查询结果
            ResultSet resultSet=hive_ps.executeQuery();
            while (resultSet.next()){
//                System.out.println(resultSet.getString(1));
//                System.out.println(resultSet.getInt(2));
                //把处理的结果写入到mysql
                PreparedStatement mysql_ps=mysql_conn.prepareStatement("insert into top3provincename values(?)");
                //赋值
                mysql_ps.setString(1, resultSet.getString(1));
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
        logNeed4();
    }
}
