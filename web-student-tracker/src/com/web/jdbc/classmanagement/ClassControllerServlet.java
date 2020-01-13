package com.web.jdbc.classmanagement;


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
 * Servlet implementation class ClassControllerServlet
 */
@WebServlet("/ClassControllerServlet")
public class ClassControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClassDbUtil classDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our student db util ... and pass in the conn pool / datasource
		try {
			classDbUtil = new ClassDbUtil(dataSource);
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
				listClasses(request, response);
				break;
				
			case "ADD":
				addClass(request,response);
				break;
			case "LOAD":
				loadClass(request,response);
				break;
			case "UPDATE":
				updateClass(request, response);
			    break;
			case "DELETE":
				deleteClass(request, response);
				break;
			case "SEARCH":
                searchClasses(request, response);
                break;
			
			}
		// list the class ... in MVC fashion
		listClasses(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	private void searchClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<Class> classes = classDbUtil.searchClasses(theSearchName);
        
        // add students to the request
        request.setAttribute("CLASS_LIST", classes);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClassManage/list-classes.jsp");
        dispatcher.forward(request, response);
    }

	
	
	private void deleteClass(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
	
		// read class id from form data
		String theClassId = request.getParameter("classId");
		
		//delete student from database
		classDbUtil.deleteClass(theClassId);
		//send them back to "list classes" page
		listClasses(request, response);
		
		
		
	}





	private void updateClass(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		//read class infor from form data
		int malop=Integer.parseInt(request.getParameter("classId"));
		String tenlop = request.getParameter("tenlop");
		int makhoa=Integer.parseInt(request.getParameter("makhoa"));
		String tenkhoa = request.getParameter("tenkhoa");
		int siso=Integer.parseInt(request.getParameter("siso"));
		boolean loailop=Boolean.parseBoolean("loailop");
		//create a new student object
		Class theClass = new Class(malop, tenlop, makhoa, tenkhoa, siso, loailop);
		
		//perform update on database
		classDbUtil.updateClass(theClass);
		
		//send them abck to the "list classes" page
		listClasses(request, response);
	}





	private void loadClass(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		
		//read class id from form data
		String theClassId = request.getParameter("classId");
		//get class from database (db util)
		Class theClass = classDbUtil.getClass(theClassId);
		//place class in the request attribute
		request.setAttribute("THE_CLASS", theClass);
		//send to jsp page: update-class-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("ClassManage/update-class-form.jsp");
		dispatcher.forward(request, response);
	}





	private void addClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// read class infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
		String tenlop = request.getParameter("tenlop");
		int makhoa=Integer.parseInt(request.getParameter("makhoa"));
		int siso=Integer.parseInt(request.getParameter("siso"));
		boolean loailop = Boolean.parseBoolean("loailop");
		//create a new class object
		Class theClass = new Class(tenlop, makhoa, siso, loailop);
		
		// add the student to the database
		classDbUtil.addClass(theClass);
		// send back to main page (the class list)
		listClasses(request, response);
	}





	private void listClasses(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//get class from db util
		List<Class> classes = classDbUtil.getClasses();
		//add classes to the request
		request.setAttribute("CLASS_LIST", classes);
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClassManage/list-classes.jsp");
		dispatcher.forward(request, response);
	}
	
	

}
