//package com.web.jdbc.usermanagement.utils;

//
//import javax.sql.DataSource;
//
//import com.web.jdbc.usermanagement.bean.UserAccount;
//import com.web.jdbc.usermanagement.config.SecurityConfig;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DataDAO {
//	
//	static UserUtil userUtil;
//	
//    private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();
// 
//    static {
//        initUsers();
//    }
// 


package com.web.jdbc.usermanagement.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.web.jdbc.usermanagement.bean.UserAccount;


/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/create")
public class DataDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static UserUtil userUtil;
	
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
			
			case "LIST":
				initUsers(request, response);
				break;
			}
		// list the sudents ... in MVC fashion
			initUsers(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void initUsers(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		try {
    		List<UserAccount> accounts = new ArrayList<>();
    		accounts = userUtil.getAccounts();
    		for (int i = 0; i < accounts.size();i++) { 		      
    			UserAccount userAccount = accounts.get(i);
    			mapUsers.put(userAccount.getUserName(), userAccount);
    			
            }  
    		response.sendRedirect("NewsControllerServlet");
    	}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
  private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();
//
//  static {
//      initUsers();
//  }
 
    // Tìm kiếm người dùng theo userName và password.
    public static UserAccount findUser(String userName, String password) {
        UserAccount u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
 
}


