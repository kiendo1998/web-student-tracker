package com.web.jdbc.faculitymanagement;


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
@WebServlet("/FaculityControllerServlet")
public class FaculityControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FaculityDbUtil faculityDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our student db util ... and pass in the conn pool / datasource
		try {
			faculityDbUtil = new FaculityDbUtil(dataSource);
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
				listFaculities(request, response);
				break;
				
			case "ADD":
				addFaculity(request,response);
				break;
			case "LOAD":
				loadFaculity(request,response);
				break;
			case "UPDATE":
				updateFaculity(request, response);
			    break;
			case "DELETE":
				deleteFaculity(request, response);
				break;
			case "SEARCH":
                searchFaculities(request, response);
                break;
			
			}
		// list the sudents ... in MVC fashion
			listFaculities(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	private void searchFaculities(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<Faculity> faculities = faculityDbUtil.searchFaculities(theSearchName);
        
        // add students to the request
        request.setAttribute("FACULITY_LIST", faculities);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("FaculityManage/list-faculities.jsp");
        dispatcher.forward(request, response);
    }

	
	
	private void deleteFaculity(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
	
		// read student id from form data
		String theFaculityId = request.getParameter("faculityId");
		
		//delete student from database
		faculityDbUtil.deleteFaculity(theFaculityId);
		//send them back to "list students" page
		listFaculities(request, response);
		
		
		
	}





	private void updateFaculity(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		//read student infor from form data
		int makhoa=Integer.parseInt(request.getParameter("faculityId"));
		String tenkhoa = request.getParameter("tenkhoa");
		String sdt = request.getParameter("sdt");
		
		//create a new student object
		Faculity theFaculity = new Faculity(makhoa, tenkhoa, sdt);
		
		//perform update on database
		faculityDbUtil.updateFaculity(theFaculity);
		
		//send them abck to the "list students" page
		listFaculities(request, response);
	}





	private void loadFaculity(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		
		//read student id from form data
		String theFaculityId = request.getParameter("faculityId");
		//get student from database (db util)
		Faculity theFaculity = faculityDbUtil.getFaculity(theFaculityId);
		//place student in the request attribute
		request.setAttribute("THE_FACULITY", theFaculity);
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("FaculityManage/update-faculity-form.jsp");
		dispatcher.forward(request, response);
	}





	private void addFaculity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// read student infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
		String tenkhoa = request.getParameter("tenkhoa");
		String sdt = request.getParameter("sdt");
		
		
		//create a new student object
		Faculity theFaculity = new Faculity(tenkhoa,sdt);
		
		// add the student to the database
		faculityDbUtil.addFaculity(theFaculity);
		// send back to main page (the student list)
		listFaculities(request, response);
	}





	private void listFaculities(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//get students from db util
		List<Faculity> faculities = faculityDbUtil.getFaculities();
		//add students to the request
		request.setAttribute("FACULITY_LIST", faculities);
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/FaculityManage/list-faculities.jsp");
		dispatcher.forward(request, response);
	}
	
	

}
