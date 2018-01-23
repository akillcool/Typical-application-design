package com.teacher.web.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JdbcUtils_C3P0 {
	private static ComboPooledDataSource ds=null;
	static {
		try{
			ds=new ComboPooledDataSource("fix");
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection() throws SQLException
	{
		return ds.getConnection();
	}
	public static void release(Connection conn ,Statement st,ResultSet rs)
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		if(st!=null)
		{
			try {
				st.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	public static DataSource getDataSource() {
		// TODO Auto-generated method stub
		return ds;
	}
	

}
