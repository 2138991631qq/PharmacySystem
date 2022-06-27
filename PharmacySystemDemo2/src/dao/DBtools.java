package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBtools {
	//数据库驱动包
	static String driver="com.mysql.jdbc.Driver";
	//与数据库建立连接
	static String url="jdbc:mysql://localhost:3306/pharmacy";
	static String user="root";
	static String password="root123";
	static Connection conn=null;
	
	//静态构造（代码）块，用于初始化加载驱动
	//定义静态方法1：连接数据库
	public static Connection getConn(){//返回值类型
		try {
			try {
				Class.forName(driver);
				conn= DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//定义静态方法2：关闭静态资源
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}}
}
