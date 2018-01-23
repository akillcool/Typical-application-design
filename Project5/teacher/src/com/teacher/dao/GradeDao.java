package com.teacher.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.teacher.pojo.Grade;
import com.teacher.pojo.Offer;
import com.teacher.pojo.Take;
import com.teacher.web.utils.JdbcUtils_C3P0;
import com.teacher.web.utils.WebUtils;

public class GradeDao {
	private CourseDao cdao=new CourseDao();
	public List<Grade> findBySerach(String str) throws SQLException
	{
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		String s[]=str.split(",");
		String cName=s[0];
		String semster=s[1];
		String year =s[2];
		String sql="select takeId,cName,year,semester,score,time,sName,student.studentId from offer,course,student,take where take.offerId=offer.offerId and  take.studentId=student.studentId and "
				+ " offer.offerId=course.courseId and "
				+ " course.cName= ? and offer.semester =? and offer.year =?  order By score desc";
		Object[]params= {cName,semster,year};
		List<Grade> list = (List<Grade>) qr.query(sql, params, new BeanListHandler(Grade.class));
		return list;
	}

	public void addGrade(Grade grade) throws SQLException {
		QueryRunner qr=new QueryRunner(JdbcUtils_C3P0.getDataSource());
		
		//create offer
		Offer offer=new Offer();
		int id =(int)System.currentTimeMillis();
		offer.setOfferId(id);
		offer.setCourseId(grade.getCourseId());
		offer.setTeacherId(grade.getTeacherId());
		offer.setYear(grade.getYear());
		offer.setSemester(grade.getSemester());
		//create take
		Take take=new Take();
		take.setOfferId(offer.getOfferId());
		take.setStudentId(grade.getStudentId());
		take.setTakeId(grade.getTakeId());
		take.setScore(grade.getScore());
		take.setTime(grade.getTime());
		//insert offer and take into database
		String sql="insert into offer (offerId,courseId,teacherId,year,semester) values(?,?,?,?,?)";
		Object params[]= {offer.getOfferId(),offer.getCourseId(),offer.getTeacherId(),offer.getYear(),offer.getSemester()};
		qr.update(sql,params);
		sql="insert into take (takeId,offerId,studentId,score,Time) values(?,?,?,?,?)";
		Object params1[]= {take.getTakeId(),take.getOfferId(),take.getStudentId(),take.getScore(),take.getTime()};
		qr.update(sql, params1);
		
	}
}
