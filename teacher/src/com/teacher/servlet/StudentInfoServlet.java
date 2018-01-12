package com.teacher.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacher.pojo.Student;
import com.teacher.service.StudentService;

/**
 * Servlet implementation class StudentInfoServlet
 */
@WebServlet("/StudentInfoServlet")
public class StudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentService studentService=new StudentService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String offerId=request.getParameter("offerId");
		try {
			List<Student> studentList = studentService.findByOfferId(Integer.parseInt(offerId));
			request.setAttribute("StudentList", studentList);
			request.getRequestDispatcher("/studentDetail.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
