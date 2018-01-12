package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacher.pojo.Course;
import com.teacher.service.TeacherService;



/**
 * Servlet implementation class SearchTeacherServlet
 */
@WebServlet("/SearchTeacherServlet")
public class SearchTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TeacherService teacherService =new TeacherService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tj=request.getParameter("tiaojian");
		String inf=request.getParameter("keyword");
		if("mohu".equals(tj)){
			List<Course> list=teacherService.blurSearch(inf);
			request.setAttribute("CourseList", list);
			request.getRequestDispatcher("/course.jsp").forward(request, response);
			return ;
			
		}else if("key".equals(tj)){
			String kyw=request.getParameter("guanjian");
			List<Course>list=teacherService.rigourSearch(kyw,inf);
			System.out.println(list);
			request.setAttribute("CourseList", list);
			request.getRequestDispatcher("/course.jsp").forward(request, response);
			return ;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
