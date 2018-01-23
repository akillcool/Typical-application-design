package com.teacher.service;

import java.sql.SQLException;

import com.teacher.dao.TakeDao;

public class TakeService {
	private TakeDao dao=new TakeDao();
	public void changeScore(double score,int takeId) throws SQLException {
		dao.changeScore(score,takeId);
		
	}

}
