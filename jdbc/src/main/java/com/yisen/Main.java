package com.yisen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author : yisen
 * @Date : 2023/4/14 10:36
 * @Description :
 */
public class Main {


	public static void main(String[] args) throws Exception {
		//1.加载驱动程序
		Class.forName("com.mysql.cj.jdbc.Driver");

		//2. 获得数据库连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yisen", "root", "root");
		//3.操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id ,name, age FROM user");
		//如果有数据，rs.next()返回true
		while (rs.next()) {
			System.out.println(rs.getString("name") + " 年龄：" + rs.getInt("age"));
		}

//docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=root -d arm64v8/mysql:latest
	}


}