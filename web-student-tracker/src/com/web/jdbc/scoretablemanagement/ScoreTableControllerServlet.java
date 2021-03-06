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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/ScoreTableControllerServlet")
public class ScoreTableControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScoreTableDbUtil scoretableDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
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
			case "SHOW":
				showAdd(request, response);
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
		
		try {
		// read student infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
			if (request.getParameter("mamh").equals("")||request.getParameter("magv").equals("")||request.getParameter("kyhoc").equals("")||request.getParameter("lichhoc").equals("")) {
				String errorMessage = "Không để trống các trường";
	            request.setAttribute("errorMessage", errorMessage);
	            RequestDispatcher dispatcher //
	                    = this.getServletContext().getRequestDispatcher("/ScoreTableControllerServlet?command=SHOW");
	            dispatcher.forward(request, response);
	            return;
			}
		int maMH=Integer.parseInt(request.getParameter("mamh"));
		int maGV=Integer.parseInt(request.getParameter("magv"));
		String kyHoc = request.getParameter("kyhoc");
		String LichHoc = request.getParameter("lichhoc");
		
		ScoreTable theScoretable = new ScoreTable(maMH, kyHoc, maGV, LichHoc);
		
		// add the student to the database
		scoretableDbUtil.addScoretable(theScoretable);
		// send back to main page (the student list)
		listScoretables(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không nhập chuỗi cho mã môn học và mã giảng viên";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/ScoreTableControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}catch(MySQLIntegrityConstraintViolationException ex) {
			String errorMessage = "Mã môn học, mã giảng viên, kỳ học phải có trong danh sách";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
            		= this.getServletContext().getRequestDispatcher("/ScoreTableControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}catch(Exception e) {
			String errorMessage = "Nhập thông tin không đúng";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/ClassControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}
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
				List<Score> scores = scoretableDbUtil.getScores(theScoreId);
				request.setAttribute("SCORE_LIST", scores);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ScoretableManage/add-scores.jsp");
				dispatcher.forward(request, response);
			}
	
	private void addScores(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
				int scoretableid=Integer.parseInt(request.getParameter("scoretableId"));
				if (request.getParameter("masv").equals("")||request.getParameter("dqt").equals("")||request.getParameter("diemthi").equals("")) {
					String errorMessage = "Không để trống các trường";
		            request.setAttribute("errorMessage", errorMessage);
		            RequestDispatcher dispatcher //
		                    = this.getServletContext().getRequestDispatcher("/ScoreTableControllerServlet?command=LOAD");
		            dispatcher.forward(request, response);
		            return;
				}
				int masv=Integer.parseInt(request.getParameter("masv"));
				float dqt=Float.parseFloat(request.getParameter("dqt"));
				float diemthi=Float.parseFloat(request.getParameter("diemthi"));
				Score theScore = new Score(scoretableid,masv, dqt, diemthi);
				scoretableDbUtil.addScore(theScore);
			loadScore(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không nhập chuỗi cho điểm, mã sinh viên";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/ScoreTableControllerServlet?command=LOAD");
            dispatcher.forward(request, response);
            return;
		}catch(MySQLIntegrityConstraintViolationException ex) {
			String errorMessage = "Mã sinh viên phải có trong danh sách sinh viên";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
            		= this.getServletContext().getRequestDispatcher("/ScoreTableControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}catch(Exception e) {
			String errorMessage = "Nhập thông tin không đúng";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/ClassControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}
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
	
	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ScoretableManage/add-scoretable-form.jsp");
		dispatcher.forward(request, response);
	}

}
