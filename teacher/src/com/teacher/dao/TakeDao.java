package com.teacher.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.teacher.pojo.Offer;
import com.teacher.pojo.Take;
import com.teacher.web.utils.JdbcUtils_C3P0;

public class TakeDao {
	public List<Take> findByOfferId(int offerId) throws SQLException
	{
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select *from take where offerId=? ";
		List<Take> list=(List<Take>) qr.query(sql, offerId,new BeanListHandler(Take.class));
		return list;
	}
	
	public List<Take> findAll() throws SQLException
	{
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select *from take ";
		List<Take> list=(List<Take>) qr.query(sql, new BeanListHandler(Take.class));
		return list;
	}

	public void changeScore(double score ,int takeId) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="update take set score =? where takeId=? ";
		Object params[]= {score,takeId};
		qr.update(sql, params);
		
	}

}
