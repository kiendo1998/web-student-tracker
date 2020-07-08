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
				System.out.println(username);
				List<Diem> diems = userUtil.getD(username);
				request.setAttribute("D_LIST", diems);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ForUser/xemdiem.jsp");
				dispatcher.forward(request, response);

			}
	
	}
