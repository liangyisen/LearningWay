package com.yisen.distributedTransaction;

import java.sql.*;

public class ConnectMysqlDemo {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        String user = "root";
        String password = "root";
        try {
            //1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取连接
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement statement = conn.createStatement();
            ResultSet execute = statement.executeQuery("select * from eason.mac_vod limit 1;");
            // 展开结果集数据库
            while(execute.next()){
                // 通过字段检索
                System.out.println("execute.getString(\"d_name  \") = " + execute.getString("d_name"));
            }
            // 完成后关闭
            execute.close();
            statement.close();
            conn.close();



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("连接成功");
    }
}
