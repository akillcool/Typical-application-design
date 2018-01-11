package com.teacher.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teacher.dao.CourseDao;
import com.teacher.dao.GradeDao;
import com.teacher.dao.OfferDao;
import com.teacher.dao.StudentDao;
import com.teacher.dao.TakeDao;
import com.teacher.pojo.Course;
import com.teacher.pojo.Grade;
import com.teacher.pojo.Offer;
import com.teacher.pojo.Student;
import com.teacher.pojo.Take;

public class GradeService {
	private CourseDao cdao=new CourseDao();
	private OfferDao odao=new OfferDao();
	private StudentDao sdao=new StudentDao();
	private TakeDao tdao=new TakeDao();
	private GradeDao gdao =new GradeDao();
	public List<Grade> getAll() throws SQLException {
		List<Grade> list=new ArrayList<Grade>();
		List<Take> tlist=tdao.findAll();
		for(Take take:tlist)
		{
			Grade grade=new Grade();
			Student s=(Student) sdao.findByStudentId(take.getStudentId());
			Offer offer=odao.findByOfferId(take.getOfferId());
			Course course=cdao.findById(offer.getCourseId());
			grade.setcName(course.getcName());
			grade.setScore(take.getScore());
			grade.setStudentId(s.getStudentId());
			grade.setsName(s.getsName());
			grade.setSemester(offer.getSemester());
			grade.setTime(take.getTime());
			grade.setYear(offer.getYear());
			grade.setTakeId(take.getTakeId());
			list.add(grade);
		}
		return list;
	}
	public List<Grade> findBySearch(String str) throws SQLException {
		
		return gdao.findBySerach(str);
	}
	public void addGrade(Grade grade) throws SQLException {
		gdao.addGrade(grade);
		
	}
	

}
