package com.web.jdbc.foruser;


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

import com.web.jdbc.scoretablemanagement.Score;
import com.web.jdbc.scoretablemanagement.ScoreTable;
import com.web.jdbc.studentscoremanagement.StudentScore;
import com.web.jdbc.usermanagement.bean.UserAccount;
import com.web.jdbc.usermanagement.utils.UserUtil;


/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserUtil userUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our student db util ... and pass in the conn pool / datasource
		try {
			userUtil = new UserUtil(dataSource);
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
			
			case "TKB":
				xemTKB(request, response);
				break;
			case "LD":
				xemLichDay(request, response);
				break;
			case "XD":
				xemDiem(request, response);
				break;
			case "ND":
				listScoretables(request, response);
				break;
			case "DKMH":
				listRegistedScoreTable(request, response);
				break;
			case "LOAD":
				loadScore(request,response);
				break;
			case "REGISTER":
				registerSubject(request,response);
				break;
			case "ADD1":
				addScores(request,response);
				break;
			case "LIST1":
				listScores(request,response);
				break;
			}
				xemTKB(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	


	private void xemTKB(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		//read class infor from form data
		String username = request.getParameter("username");
		System.out.println(username);
		List<TKB> tkbs = userUtil.getTKB(username);
		request.setAttribute("TKB_LIST", tkbs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ForUser/TKB.jsp");
		dispatcher.forward(request, response);

	}
	
	private void xemLichDay(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
				
				//read class infor from form data
				String username = request.getParameter("username");
				System.out.println(username);
				List<TKB> tkbs = userUtil.getLD(username);
				request.setAttribute("LD_LIST", tkbs);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ForUser/lichday.jsp");
				dispatcher.forward(request, response);

			}
	private void xemDiem(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
				
				//read class infor from form data
				String username = request.getParameter("username");
				List<Diem> diems = userUtil.getD(username);
				request.setAttribute("D_LIST", diems);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ForUser/xemdiem.jsp");
				dispatcher.forward(request, response);

			}
	private void addScores(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
				
		//read student infor from form data
		int scoretableid=Integer.parseInt(request.getParameter("scoretableId"));
		int masv=Integer.parseInt(request.getParameter("masv"));
		float dqt=Float.parseFloat(request.getParameter("dqt"));
		float diemthi=Float.parseFloat(request.getParameter("diemthi"));
		//create a new student object
		Score theScore = new Score(scoretableid,masv, dqt, diemthi);
		
		//perform update on database
		userUtil.addScore(theScore);
		
		//send them abck to the "list students" page
//		loadScore(request, response);
//	listScores(request, response);
	loadScore(request, response);

			}
	private void loadScore(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read student id from form data
		String theScoretableId = request.getParameter("scoretableId");
		//get student from database (db util)
		ScoreTable theScoretable = userUtil.getScoretable(theScoretableId);
		//place student in the request attribute
		request.setAttribute("THE_SCORETABLE", theScoretable);
		listScores(request, response);
	}
	private void listScores(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theScoreId = request.getParameter("scoretableId");
				List<Score> scores = userUtil.getScores(theScoreId);
				//add students to the request
				request.setAttribute("SCORE_LIST", scores);
				//send to jsp page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InputScoreForLecturer/add-scores.jsp");
				dispatcher.forward(request, response);
	}
	
	private void listScoretables(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				String username = request.getParameter("username");
				List<ScoreTable> scoretables = userUtil.getScoretables(username);
				request.setAttribute("SCORETABLE_LIST", scoretables);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InputScoreForLecturer/list-scoretables.jsp");
				dispatcher.forward(request, response);
	}
	private void listRegistedScoreTable(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				String username = request.getParameter("username");
				List<ScoreTable> scoretables = userUtil.getRegistedScoretables(username);
				request.setAttribute("REGISTED_LIST", scoretables);
				List<ScoreTable> scoretables1 = userUtil.getScoretablesToRegister(username);
				request.setAttribute("TOREGISTED_LIST", scoretables1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/RegisterForStudent/list-scoretables.jsp");
				dispatcher.forward(request, response);
	}
	private void registerSubject(HttpServletRequest request, HttpServletResponse response) throws Exception{
			
		//read student infor from form data
				int scoretableid=Integer.parseInt(request.getParameter("scoretableId"));
				String username=request.getParameter("username");
				int masv = userUtil.getMaSV(username);
				//create a new student object
				Score theScore = new Score(masv,scoretableid);
				System.out.println("hello"+scoretableid);
				//perform update on database
				userUtil.registerSubject(theScore);
				
				//send them abck to the "list students" page
//				loadScore(request, response);
//			listScores(request, response);
				listRegistedScoreTable(request, response);
		}


}
