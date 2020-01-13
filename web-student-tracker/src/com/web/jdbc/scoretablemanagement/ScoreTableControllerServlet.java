package com.web.jdbc.scoretablemanagement;


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
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/ScoreTableControllerServlet")
public class ScoreTableControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScoreTableDbUtil scoretableDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our student db util ... and pass in the conn pool / datasource
		try {
			scoretableDbUtil = new ScoreTableDbUtil(dataSource);
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
				theCommand = "LIST";
				
			}
			
			//route to the appropriate mathod
			switch (theCommand) {
			
			case "LIST":
				listScoretables(request, response);
				break;
				
			case "ADD":
				addScoretable(request,response);
				break;
			case "ADD1":
				addScores(request,response);
				break;
			case "LIST1":
				listScores(request,response);
				break;
//			case "LIST2":
//				listScores1(request,response);
//				break;
			case "LOAD":
				loadScore(request,response);
				break;
//			case "LOAD1":
//				loadScores(request,response);
//				break;
//			case "UPDATE":
//				updateScoretable(request, response);
//			    break;
			case "DELETE":
				deleteScoretable(request, response);
				break;
			case "SEARCH":
                searchScoretables(request, response);
                break;
			
			}
		// list the sudents ... in MVC fashion
		listScoretables(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	private void searchScoretables(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<ScoreTable> scoretables = scoretableDbUtil.searchScoretables(theSearchName);
        
        // add students to the request
        request.setAttribute("SCORETABLE_LIST", scoretables);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("ScoretableManage/list-scoretables.jsp");
        dispatcher.forward(request, response);
    }

	
	
	private void deleteScoretable(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
	
		// read student id from form data
		String theScoretableId = request.getParameter("scoretableId");
		
		//delete student from database
		scoretableDbUtil.deleteScoretable(theScoretableId);
		//send them back to "list students" page
		listScoretables(request, response);
		
		
		
	}





//	private void updateScoretable(HttpServletRequest request, HttpServletResponse response) 
//	throws Exception {
//		
//		//read student infor from form data
//		int scoreTableId=Integer.parseInt(request.getParameter("scoreTableId"));
//		int MaMH=Integer.parseInt(request.getParameter("MaMH"));
//		int MaLop=Integer.parseInt(request.getParameter("MaLop"));
//		String KyHoc = request.getParameter("KyHoc");
//		//create a new student object
//		ScoreTable theScoretable = new ScoreTable(scoreTableId, MaMH, MaLop, KyHoc);
//		
//		//perform update on database
//		scoretableDbUtil.updateScoretable(theScoretable);
//		
//		//send them abck to the "list students" page
//		listScoretables(request, response);
//	}





//	private void loadScoretable(HttpServletRequest request, HttpServletResponse response) 
//	throws Exception{
//		
//		//read student id from form data
//		String theScoretableId = request.getParameter("scoretableId");
//		//get student from database (db util)
//		ScoreTable theScoretable = scoretableDbUtil.getScoretable(theScoretableId);
//		//place student in the request attribute
//		request.setAttribute("THE_SCORETABLE", theScoretable);
//		//send to jsp page: update-student-form.jsp
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("ScoretableManage/update-scoretable-form.jsp");
//		dispatcher.forward(request, response);
//	}





	private void addScoretable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// read student infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
		int maMH=Integer.parseInt(request.getParameter("mamh"));
		int maLop=Integer.parseInt(request.getParameter("malop"));
		String kyHoc = request.getParameter("kyhoc");
		
		ScoreTable theScoretable = new ScoreTable(maMH, maLop, kyHoc);
		
		// add the student to the database
		scoretableDbUtil.addScoretable(theScoretable);
		// send back to main page (the student list)
		listScoretables(request, response);
	}





	private void listScoretables(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//get students from db util
		List<ScoreTable> scoretables = scoretableDbUtil.getScoretables();
		//add students to the request
		request.setAttribute("SCORETABLE_LIST", scoretables);
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ScoretableManage/list-scoretables.jsp");
		dispatcher.forward(request, response);
	}
	private void listScores(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theScoreId = request.getParameter("scoretableId");
		//get student from database (db util)
//		Score theScore = scoretableDbUtil.getScoretable(theScoreId);
				//get students from db util
				List<Score> scores = scoretableDbUtil.getScores(theScoreId);
				//add students to the request
				request.setAttribute("SCORE_LIST", scores);
				//send to jsp page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ScoretableManage/add-scores.jsp");
				dispatcher.forward(request, response);
			}
	
	private void addScores(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
				//read student infor from form data
				int scoretableid=Integer.parseInt(request.getParameter("scoretableId"));
				int masv=Integer.parseInt(request.getParameter("masv"));
				float dqt=Float.parseFloat(request.getParameter("dqt"));
				float diemthi=Float.parseFloat(request.getParameter("diemthi"));
				//create a new student object
				Score theScore = new Score(scoretableid,masv, dqt, diemthi);
				
				//perform update on database
				scoretableDbUtil.addScore(theScore);
				
				//send them abck to the "list students" page
//				loadScore(request, response);
//			listScores(request, response);
			loadScore(request, response);
			}
	private void loadScore(HttpServletRequest request, HttpServletResponse response) throws Exception{
				
				//read student id from form data
				String theScoretableId = request.getParameter("scoretableId");
				//get student from database (db util)
				ScoreTable theScoretable = scoretableDbUtil.getScoretable(theScoretableId);
				//place student in the request attribute
				request.setAttribute("THE_SCORETABLE", theScoretable);
				//send to jsp page: update-student-form.jsp
//				RequestDispatcher dispatcher = request.getRequestDispatcher("ScoretableManage/add-scores.jsp");
//				dispatcher.forward(request, response);
				listScores(request, response);
			}
	
	

}
