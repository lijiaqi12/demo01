package com.group.one.logNeed;

import com.group.one.utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logDemo6 {
    /**
     * 需求6 计算山西省的每种农产品的价格波动趋势，即计算每天价格均值。
     */
    public static void logNeed6(){
        Connection hive_conn= ConnectUtils.getHive_conn();
        Connection mysql_conn=ConnectUtils.getMysql_conn();
        //写代码
        PreparedStatement hive_ps= null;
        try {
            hive_ps = hive_conn.prepareStatement("select t_farm.province,t_farm.times,t_farm.name,\n" +
                    "round(if(count(*)>2,\n" +
                    "(sum(price)-max(price)-min(price))/(count(*)-2),\n" +
                    "sum(price)/count(*)),2)as avgsss\n" +
                    "from\n" +
                    "(select *from t_farm1 where province=\"山西\"\n" +
                    "union all\n" +
                    "select *from t_farm2 where province=\"山西\"\n" +
                    "union all\n" +
                    "select *from t_farm3 where province=\"山西\"\n" +
                    "union all\n" +
                    "select *from t_farm4 where province=\"山西\"\n" +
                    "union all\n" +
                    "select *from t_farm5 where province=\"山西\"\n" +
                    ") t_farm \n" +
                    "group by t_farm.province,t_farm.times,t_farm.name");
            //获取查询结果
            ResultSet resultSet=hive_ps.executeQuery();
            while (resultSet.next()){
//                System.out.println(resultSet.getString(1));
//                System.out.println(resultSet.getInt(2));
                //把处理的结果写入到mysql
                PreparedStatement mysql_ps=mysql_conn.prepareStatement("insert into shanxi values(?,?,?,?)");
                //赋值
                mysql_ps.setString(1, resultSet.getString(1));
                mysql_ps.setString(2, resultSet.getString(2));
                mysql_ps.setString(3, resultSet.getString(3));
                mysql_ps.setFloat(4, resultSet.getFloat(4));
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
        logNeed6();
    }
}
