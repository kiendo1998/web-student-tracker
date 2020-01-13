package com.web.jdbc.subjectmanagement;

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
@WebServlet("/SubjectControllerServlet")
public class SubjectControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDbUtil subjectDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

	
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our subject db util ... and pass in the conn pool / datasource
				try {
					subjectDbUtil = new SubjectDbUtil(dataSource);
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
			listSubjects(request, response);
			break;
		case "SADD":
			addSubject(request,response);
			break;
		case "SLOAD":
			loadSubject(request,response);
			break;
		case "SUPDATE":
			updateSubject(request,response);
			break;
		case "SDELETE":
			deleteSubject(request,response);
			break;
		case "SSEARCH":
			searchSubject(request,response);
			break;
		default:
			listSubjects(request, response);
		
		}
	// list the sudents ... in MVC fashion
	listSubjects(request, response);
	}
	catch (Exception exc) {
		throw new ServletException(exc);
	}
    }
	
	
	private void searchSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search Subjects from db util
        List<Subject> subjects = subjectDbUtil.searchSubjects(theSearchName);
        
        // add subjects to the request
        request.setAttribute("SUBJECT_LIST", subjects);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectManage/subject-manage-home.jsp");
        dispatcher.forward(request, response);
		
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read subject id from form data
				String theSubjectId = request.getParameter("subjectId");
				
				//delete subject from database
				subjectDbUtil.deleteSubject(theSubjectId);
				//send them back to "list subjects" page
				listSubjects(request, response);
		
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		//read subject infor from form data
				int mamh=Integer.parseInt(request.getParameter("subjectId"));
				String tenmh = request.getParameter("tenmh");
				int sotc=Integer.parseInt(request.getParameter("sotc"));
				
				//create a new subject object
				Subject theSubject = new Subject(mamh, tenmh, sotc);
				
				//perform update on database
				subjectDbUtil.updateSubject(theSubject);
				
				//send them abck to the "list students" page
				listSubjects(request, response);
		
	}

	private void loadSubject(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		//read subject id from form data
				String theSubjectId = request.getParameter("subjectId");
				//get subject from database (db util)
				Subject theSubject = subjectDbUtil.getSubject(theSubjectId);
				//place subject in the request attribute
				request.setAttribute("THE_SUBJECT", theSubject);
				//send to jsp page: update-student-form.jsp
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("SubjectManage/update-subject-form.jsp");
				dispatcher.forward(request, response);
		
	}

	private void addSubject(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// read subject infor from form data
		String tenmh = request.getParameter("tenmh");
		int sotc=Integer.parseInt(request.getParameter("sotc"));
				
				//create a new subject object
				Subject theSubject = new Subject(tenmh, sotc);
				
				// add the subject to the database
				subjectDbUtil.addSubject(theSubject);
				// send back to main page (the subject list)
				listSubjects(request, response);
		
	}

	private void listSubjects(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		//get subjects from db util
				List<Subject> subjects = subjectDbUtil.getSubjects();
				//add subjects to the request
				request.setAttribute("SUBJECT_LIST", subjects);
				//send to jsp page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectManage/subject-manage-home.jsp");
				dispatcher.forward(request, response);
		
	}
	
	
}


