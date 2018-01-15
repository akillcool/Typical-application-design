package com.teacher.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacher.web.utils.JdbcUtils_C3P0;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 ServletContext servletContext = this.getServletConfig().getServletContext();
		 //compile rxml file，create jasper file
		 try {
			InputStream in=servletContext.getResourceAsStream(("/reports/Report.jasper"));
			ServletOutputStream outputStream = response.getOutputStream();
			String printfileName=JasperFillManager.fillReportToFile(servletContext.getRealPath("/reports/Report.jasper"), null,JdbcUtils_C3P0.getConnection());
			// export pdf file
			String pathFile="/root/tempjasperdocs"+"report"+System.currentTimeMillis()+".pdf";
			JasperExportManager.exportReportToPdfFile(printfileName,pathFile);
			
			//html file
			//JasperRunManager.runReportToHtmlFile(servletContext.getRealPath("/reports/Report.jasper"),pathFile,null,JdbcUtils_C3P0.getConnection());
			//pdf file
			response.setContentType("application/pdf");
			response.setCharacterEncoding("UTF-8");  
			JasperRunManager.runReportToPdfStream(in, outputStream,null,JdbcUtils_C3P0.getConnection());
			
			outputStream.flush();
			outputStream.close();
		
			
		} catch (JRException e) {
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
	
	public static void main(String[] args) throws JRException {
		
		JasperCompileManager.compileReportToFile("/root/tempjasperdocs");
	}

}
