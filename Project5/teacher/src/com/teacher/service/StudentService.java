package com.teacher.service;

import java.sql.SQLException;
import java.util.List;

import com.teacher.dao.StudentDao;
import com.teacher.pojo.Student;

public class StudentService {
	private StudentDao dao=new StudentDao();
	public List<Student> findByOfferId(int offerId) throws SQLException
	{
		return dao.findByOfferId(offerId);
	}

}
