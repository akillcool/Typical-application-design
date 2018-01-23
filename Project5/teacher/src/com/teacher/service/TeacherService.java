package com.teacher.service;

import java.util.List;

import com.teacher.dao.TeacherDao;
import com.teacher.pojo.Course;

public class TeacherService {
	private TeacherDao dao=new TeacherDao();
	public List<Course> rigourSearch(String kyw, String inf) {
		try {
			  return dao.rigourSearch(kyw,inf);
			} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
			}
	}
	

	public List<Course> blurSearch(String inf) {
		try {
			  return dao.blurSearch(inf);
			} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
			}
	}

}
