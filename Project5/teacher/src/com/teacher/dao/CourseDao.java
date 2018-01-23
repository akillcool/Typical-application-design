package com.teacher.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.teacher.pojo.Course;
import com.teacher.pojo.Course_teacher;
import com.teacher.web.utils.JdbcUtils_C3P0;

public class CourseDao {

	public List<Course_teacher> getAll() {
		return null;
	}
	public Course findById(int id) throws SQLException
	{
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select * from course where courseId =? ";
		Course t=(Course) qr.query(sql, id, new BeanHandler(Course.class));
		return t;
	}
	public int findByName(String getcName) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select * from course where cName =? ";
		Course t=(Course) qr.query(sql, getcName, new BeanHandler(Course.class));
		return t.getCourseId();
	}

}
