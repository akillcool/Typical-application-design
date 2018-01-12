package com.teacher.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacher.pojo.Grade;
import com.teacher.service.GradeService;

/**
 * Servlet implementation class SearchGradeServlet
 */
@WebServlet("/SearchGradeServlet")
public class SearchGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GradeService gradeService=new GradeService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchGradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseName=request.getParameter("courseName");
		String semster=request.getParameter("semster");
		String year=request.getParameter("year");
		String str=courseName+","+semster+","+year;
		List<Grade> list;
		try {
			list = gradeService.findBySearch(str);
			request.setAttribute("gradeList", list);
			request.getRequestDispatcher("/gradeList.jsp").forward(request, response);
			
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
