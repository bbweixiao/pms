package com.kaishengit.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;


public class DBHelp<T> {

	private static BasicDataSource ds = new BasicDataSource();
	static{
		Properties properties = new Properties();
		try {
			properties.load(DBHelp.class.getClassLoader().getResourceAsStream("dbconfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String DRIVER = properties.getProperty("driver");
		String URL = properties.getProperty("url");
		String USERNAME = properties.getProperty("username");
		String PASSWORD = properties.getProperty("password");
		ds.setDriverClassName(DRIVER);
		ds.setUrl(URL);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		ds.setInitialSize(5);
		ds.setMaxWait(5000);
		ds.setMaxActive(20);
		ds.setMinIdle(10);
	}
	
	/**
	 * 获取数据库连接对象
	 * @return
	 */
	public Connection getConnection() {
		try {
			Connection conn = ds.getConnection();
			return conn;
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 执行insert update delete语句
	 * @param sql
	 */
	public void executeSQL(String sql,Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = getConnection();
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			stat.executeUpdate();
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stat,conn);
		}
	}
	
	public T executeQueryForObject(String sql,RowMapper<T> mapper,Object... args){
		Connection conn = getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		T t = null;
		
		try {
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			rs = stat.executeQuery();
			if(rs.next()) {
				t = mapper.mapperRow(rs);
			}
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stat,conn);
		}
		return t;
		
	}
	
	/**
	 * 根据聚合函数进行查询
	 * @param sql
	 * @param args
	 * @return
	 */
	public int executeQueryForCount(String sql,Object... args){
		Connection conn = getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		int t = 0;
		
		try {
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			rs = stat.executeQuery();
			if(rs.next()) {
				t = rs.getInt(1);
			}
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stat,conn);
		}
		return t;
		
	}
	
	public List<T> executeQueryForList(String sql,RowMapper<T> mapper,Object... args){
		Connection conn = getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		
		try {
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			rs = stat.executeQuery();
			while(rs.next()) {
				T t = mapper.mapperRow(rs);
				list.add(t);
			}
			System.out.println("SQL:" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stat,conn);
		}
		return list;
	}
	
	/**
	 * 释放连接
	 * @param stat
	 * @param conn
	 */
	public void close(PreparedStatement stat,Connection conn) {
		close(null,stat,conn);
	}
	
	/**
	 * 释放连接
	 * @param rs
	 * @param stat
	 * @param conn
	 */
	public void close(ResultSet rs,PreparedStatement stat,Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stat != null) {
					stat.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
