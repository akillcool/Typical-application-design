package com.teacher.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teacher.dao.CourseDao;
import com.teacher.dao.OfferDao;
import com.teacher.dao.TeacherDao;
import com.teacher.pojo.Course;
import com.teacher.pojo.Course_teacher;
import com.teacher.pojo.Offer;
import com.teacher.pojo.Teacher;

public class CourseService {

	private CourseDao cdao=new CourseDao();
	private OfferDao odao=new OfferDao();
	private TeacherDao tdao=new TeacherDao();
	public List<Course_teacher> getALL() throws SQLException {
		
		List<Course_teacher> list=new ArrayList<Course_teacher>();
		List<Offer> offerlist = odao.getAll();
		for(Offer offer:offerlist)
		{
			Teacher teacher = tdao.findById(offer.getTeacherId());
			Course c = cdao.findById(offer.getCourseId());
			Course_teacher ct=new Course_teacher();
			ct.setCourseName(c.getcName());
			ct.setOfferId(offer.getOfferId());
			ct.setTeacher(teacher);
			ct.setYear(offer.getYear());
			ct.setSemester(offer.getSemester());
			list.add(ct);
		}
		return list;
	}

}
