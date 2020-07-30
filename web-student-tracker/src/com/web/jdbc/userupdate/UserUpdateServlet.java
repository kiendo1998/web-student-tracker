package com.web.jdbc.userupdate;


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

import com.web.jdbc.usermanagement.bean.UserAccount;
import com.web.jdbc.usermanagement.utils.UserUtil;


/**
 * Servlet implementation class ClassControllerServlet
 */
@WebServlet("/updateuser")
public class UserUpdateServlet extends HttpServlet {
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
				updateUser(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	


	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		//read class infor from form data
		String username0 = request.getParameter("username0");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.equals("")||password.equals("")) {
			String errorMessage = "Không để trống các trường";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/userInfo");
            dispatcher.forward(request, response);
            return;
		}
		UserAccount userAccount = new UserAccount(username,password);
		userAccount.setUserName0(username0);
		userUtil.updateUser(userAccount);
		//trong datadao
		response.sendRedirect("create?command=UPDATE");
	}




	
}
