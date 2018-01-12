package com.teacher.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.teacher.pojo.Offer;
import com.teacher.web.utils.JdbcUtils_C3P0;

public class OfferDao {
	public List<Offer> getAll() throws SQLException
	{
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select *from offer ";
		List<Offer> list=(List<Offer>) qr.query(sql, new BeanListHandler(Offer.class));
		return list;
	}
	public Offer findByOfferId(int offerId) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select *from offer where offerId=? ";
		Offer offer= (Offer) qr.query(sql, offerId,new BeanHandler(Offer.class));
		return offer;
	}


}
