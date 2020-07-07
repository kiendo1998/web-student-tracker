package com.web.jdbc.scoremanagement;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class SubjectControllerServlet
 */
@WebServlet("/ScoreControllerServlet")
public class ScoreControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreDbUtil scoreDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
	private DataSource dataSource;

	
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our subject db util ... and pass in the conn pool / datasource
				try {
					scoreDbUtil = new ScoreDbUtil(dataSource);
				}
				catch (Exception exc) {
					throw new ServletException(exc);
				}
	}





	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//read the "command" parameter
		String theCommand = request.getParameter("command");
		// if the command is missing, then default to listing students
		if (theCommand == null) {
			theCommand = "SLIST";
			
		}
		
		//route to the appropriate mathod
		switch (theCommand) {
		
		case "SLIST":
			listScores(request, response);
			break;
		case "SADD":
			addScore(request,response);
			break;
//		case "SLOAD":
//			loadScore(request,response);
//			break;
//		case "SUPDATE":
//			updateScore(request,response);
//			break;
//		case "SDELETE":
//			deleteScore(request,response);
//			break;
//		case "SSEARCH":
//			searchScore(request,response);
//			break;
		default:
			listScores(request, response);
		
		}
	// list the sudents ... in MVC fashion
	listScores(request, response);
	}
	catch (Exception exc) {
		throw new ServletException(exc);
	}
    }
	
	
//	private void searchScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// read search name from form data
//        String theSearchName = request.getParameter("theSearchName");
//        
//        // search Subjects from db util
//        List<Score> scores = scoreDbUtil.searchScores(theSearchName);
//        
//        // add subjects to the request
//        request.setAttribute("SCORE_LIST", scores);
//                
//        // send to JSP page (view)
//        RequestDispatcher dispatcher = request.getRequestDispatcher("xemdiemsv.jsp");
//        dispatcher.forward(request, response);
//		
//	}
//
//	private void deleteScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// read subject id from form data
//				String theScoreId = request.getParameter("scoreId");
//				
//				//delete subject from database
//				scoreDbUtil.deleteScore(theScoreId);
//				//send them back to "list subjects" page
//				listScores(request, response);
//		
//	}
//
//	private void updateScore(HttpServletRequest request, HttpServletResponse response) 
//	throws Exception {
//		//read subject infor from form data
//				int madiem=Integer.parseInt(request.getParameter("scoreId"));
//				int mabd=Integer.parseInt(request.getParameter("scoretableid"));
//				int masv=Integer.parseInt(request.getParameter("masv"));
//				String tensv = request.getParameter("tensv");
//				String tenlop = request.getParameter("tenlop");
//				String tenmh = request.getParameter("tenmh");
//				float dqt=Float.parseFloat(request.getParameter("diemqt"));
//				float diemthi=Float.parseFloat(request.getParameter("diemthi"));
//				float diemkt=Float.parseFloat(request.getParameter("diemkt"));
//				//create a new subject object
//				Score theScore = new Score(madiem, mabd, masv, tensv, tenlop, tenmh, dqt, diemthi, diemkt);
//				
//				//perform update on database
//				scoreDbUtil.updateScore(theScore);
//				
//				//send them abck to the "list students" page
//				listScores(request, response);
//		
//	}
//
//	private void loadScore(HttpServletRequest request, HttpServletResponse response) 
//	throws Exception{
//		//read subject id from form data
//				String theScoreId = request.getParameter("scoreId");
//				//get subject from database (db util)
//				Score theScore = scoreDbUtil.getScore(theScoreId);
//				//place subject in the request attribute
//				request.setAttribute("THE_SCORE", theScore);
//				//send to jsp page: update-student-form.jsp
//				RequestDispatcher dispatcher = 
//						request.getRequestDispatcher("xemdiemsv.jsp");
//				dispatcher.forward(request, response);
//		
//	}

	private void addScore(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// read subject infor from form data
		int masv=Integer.parseInt(request.getParameter("masv"));
		String tenlop = request.getParameter("tenlop");
		String tenmh = request.getParameter("tenmh");
		float dqt=Float.parseFloat(request.getParameter("dqt"));
		float diemthi=Float.parseFloat(request.getParameter("diemthi"));
		String kyhoc = request.getParameter("kyhoc");
				
				//create a new subject object
				Score theScore = new Score(masv, tenlop, tenmh, dqt, diemthi, kyhoc);
				
				// add the subject to the database
				scoreDbUtil.addScore(theScore);
				// send back to main page (the subject list)
				listScores(request, response);
		
	}

	private void listScores(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		//get subjects from db util
				List<Score> scores = scoreDbUtil.getScores();
				//add subjects to the request
				request.setAttribute("SCORE_LIST", scores);
				//send to jsp page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("xemdiemsv.jsp");
				dispatcher.forward(request, response);
		
	}

//	private void showAdd(HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/NewsManager/add-news-form.jsp");
//		dispatcher.forward(request, response);
//	}
//	
	
}


