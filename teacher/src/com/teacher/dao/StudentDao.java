package com.teacher.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.teacher.pojo.Course;
import com.teacher.pojo.Student;
import com.teacher.pojo.Take;
import com.teacher.web.utils.JdbcUtils_C3P0;

public class StudentDao {
	private TakeDao tdao=new TakeDao();
	public List<Student> findByOfferId(int offerId) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select *from student where studentId=? ";
		List<Student> list=new ArrayList<Student>();
		List<Take> tlist=tdao.findByOfferId(offerId);
		for(Take take:tlist)
		{
			Student s=new Student();
			s= (Student) qr.query(sql, take.getStudentId(),new BeanHandler(Student.class));
			list.add(s);
		}
		return list;
	}
	public Student findByStudentId(int studentId) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select *from student where studentId=? ";
		Student student= (Student) qr.query(sql, studentId,new BeanHandler(Student.class));
		return student;
	}

}
