package com.teacher.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacher.service.TakeService;

/**
 * Servlet implementation class ChangeScoreServlet
 */
@WebServlet("/ChangeScoreServlet")
public class ChangeScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TakeService takeService=new TakeService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeScoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String takeId= request.getParameter("takeId");
		String score=request.getParameter("score");
		try {
			takeService.changeScore(Double.parseDouble(score),Integer.parseInt(takeId));
			request.getRequestDispatcher("GradeServlet").forward(request, response);
		} catch (NumberFormatException | SQLException e) {
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
