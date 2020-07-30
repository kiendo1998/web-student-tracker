package com.web.jdbc.subjectmanagement;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class SubjectControllerServlet
 */
@WebServlet("/SubjectControllerServlet")
public class SubjectControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDbUtil subjectDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
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
		case "SHOW":
			showAdd(request, response);
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
		try {
				String theSubjectId = request.getParameter("subjectId");
				
				//delete subject from database
				subjectDbUtil.deleteSubject(theSubjectId);
				//send them back to "list subjects" page
				listSubjects(request, response);
		}catch(MySQLIntegrityConstraintViolationException ex) {
			String errorMessage = "Không xóa được do có ràng buộc ở lớp môn học";
            request.setAttribute("errorMessage", errorMessage);
            listSubjects(request, response);
            return;
		}
		
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		try {
				//read subject infor from form data	
			int mamh=Integer.parseInt(request.getParameter("subjectId"));
				String tenmh = request.getParameter("tenmh");
				//create a new subject object
				if (tenmh.equals("")||request.getParameter("sotc").equals("")) {
					String errorMessage = "Không để trống các trường";
		            request.setAttribute("errorMessage", errorMessage);
		            loadSubject(request,response);
		            return;
				}
				
				int sotc=Integer.parseInt(request.getParameter("sotc"));
				Subject theSubject = new Subject(mamh, tenmh, sotc);
				//perform update on database
				subjectDbUtil.updateSubject(theSubject);
				
				//send them abck to the "list students" page
				listSubjects(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không nhập chuỗi cho số tín chỉ";
            request.setAttribute("errorMessage", errorMessage);
            loadSubject(request,response);
            return;
		}catch(Exception e) {
			String errorMessage = "Nhập thông tin không đúng";
            request.setAttribute("errorMessage", errorMessage);
            loadSubject(request,response);
            return;
		}
		
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
		try {
			// read subject infor from form data
			String tenmh = request.getParameter("tenmh");
			if (tenmh.equals("")||request.getParameter("sotc").equals("")) {
				String errorMessage = "Không để trống các trường";
	            request.setAttribute("errorMessage", errorMessage);
	            RequestDispatcher dispatcher //
	                    = this.getServletContext().getRequestDispatcher("/SubjectControllerServlet?command=SHOW");
	            dispatcher.forward(request, response);
	            return;
			}
			int sotc=Integer.parseInt(request.getParameter("sotc"));	
			//create a new subject object
			Subject theSubject = new Subject(tenmh, sotc);
			subjectDbUtil.addSubject(theSubject);
			// send back to main page (the subject list)
			listSubjects(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không nhập chuỗi cho số tín chỉ";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/SubjectControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}catch(Exception e) {
			String errorMessage = "Nhập thông tin không đúng";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/SubjectControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}
		
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
	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SubjectManage/add-subject-form.jsp");
		dispatcher.forward(request, response);
	}
	
}


