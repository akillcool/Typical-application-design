package com.teacher.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.teacher.pojo.Course;
import com.teacher.pojo.Offer;
import com.teacher.pojo.Teacher;
import com.teacher.web.utils.JdbcUtils_C3P0;


public class TeacherDao {
	private CourseDao cdao=new CourseDao();
	public Teacher findById(int id) throws SQLException
	{
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select * from teacher where teacherId =? ";
		Teacher t=(Teacher) qr.query(sql, id, new BeanHandler(Teacher.class));
		return t;
	}

	public List<Course> blurSearch(String inf) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select offerId,offer.courseId,offer.teacherId,year,semester from offer ,teacher,course where offer.courseId=course.courseId and offer.teacherId=teacher.teacherId and "
				+ " cName like ? or teacher.teacherId like ? or tName like ? or year like ? or semester like ? ";
		String s= "%"+ inf + "%";
		List<Course> list=new ArrayList<Course>();
		Object params[]= {s,s,s,s,s};
		List<Offer> offerlist=(List<Offer>) qr.query(sql,params, new BeanListHandler(Offer.class));
		for(Offer offer :offerlist)
		{
			Course course = new Course();
			course=cdao.findById(offer.getCourseId());
			list.add(course);
		}
		return list;
		
	}
	public int findByName(String name) throws SQLException
	{
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String sql="select * from teacher where tName =? ";
		Teacher t=(Teacher) qr.query(sql, name, new BeanHandler(Teacher.class));
		return t.getTeacherId();
	}
	public List<Course> rigourSearch(String kyw, String inf) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		
		if(kyw.equals("teacherName"))
		{
			inf=findByName(inf)+"";
			
			kyw="teacherId";
		}
		String sql="select * from offer where "+kyw+"="+"'" + inf+ "'";
		List<Course> list=new ArrayList<Course>();
		List<Offer> offerlist=(List<Offer>) qr.query(sql, new BeanListHandler(Offer.class));
		for(Offer offer :offerlist)
		{
			Course course =new Course();
			course=cdao.findById(offer.getCourseId());
			list.add(course);
		}
		return list;
	}

}
