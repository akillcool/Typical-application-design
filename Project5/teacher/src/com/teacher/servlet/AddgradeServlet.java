package com.teacher.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacher.pojo.Grade;
import com.teacher.service.GradeService;
import com.teacher.web.utils.WebUtils;

/**
 * Servlet implementation class AddgradeServlet
 */
@WebServlet("/AddgradeServlet")
public class AddgradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GradeService gradeService=new GradeService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddgradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Grade grade = WebUtils.request2Bean(request, Grade.class);
		int id=(int)System.currentTimeMillis();
		grade.setTakeId(id);
		try {
			gradeService.addGrade(grade);
			request.getRequestDispatcher("GradeServlet").forward(request, response);
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
