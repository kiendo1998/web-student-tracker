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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


/**
 * Servlet implementation class ClassControllerServlet
 */
@WebServlet("/ClassControllerServlet")
public class ClassControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClassDbUtil classDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
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
			case "SHOW":
				showAdd(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ClassManage/list-classes.jsp");
        dispatcher.forward(request, response);
    }

	
	
	private void deleteClass(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
	try {
		// read class id from form data
		String theClassId = request.getParameter("classId");
		
		//delete student from database
		classDbUtil.deleteClass(theClassId);
		//send them back to "list classes" page
		listClasses(request, response);
	}catch(MySQLIntegrityConstraintViolationException ex) {
		String errorMessage = "Lỗi do mã lớp có ràng buộc với sinh viên";
        request.setAttribute("errorMessage", errorMessage);
        listClasses(request, response);
        return;
        }
		
		
		
	}





	private void updateClass(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		try {
		//read class infor from form data
		int malop=Integer.parseInt(request.getParameter("classId"));
		String tenlop = request.getParameter("tenlop");
		if (tenlop.equals("")||request.getParameter("makhoa").equals("")||request.getParameter("siso").equals("")) {
			String errorMessage = "Không để trống các trường";
            request.setAttribute("errorMessage", errorMessage);
            
            loadClass(request, response);
            return;
		}
		int makhoa=Integer.parseInt(request.getParameter("makhoa"));
		String tenkhoa = request.getParameter("tenkhoa");
		int siso=Integer.parseInt(request.getParameter("siso"));
		//create a new student object
		Class theClass = new Class(malop, tenlop, makhoa, tenkhoa, siso);
		
		//perform update on database
		classDbUtil.updateClass(theClass);
		
		//send them abck to the "list classes" page
		listClasses(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không nhập chuỗi cho mã khoa và sĩ số";
            request.setAttribute("errorMessage", errorMessage);
            loadClass(request, response);
            return;
		}catch(MySQLIntegrityConstraintViolationException ex) {
			String errorMessage = "Khoa phải có trong danh sách khoa";
            request.setAttribute("errorMessage", errorMessage);
            loadClass(request, response);
            return;
		}catch(Exception e) {
			String errorMessage = "Nhập thông tin không đúng";
            request.setAttribute("errorMessage", errorMessage);
            loadClass(request, response);
            return;
		}
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
				request.getRequestDispatcher("/ClassManage/update-class-form.jsp");
		dispatcher.forward(request, response);
	}





	private void addClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
		// read class infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
		String tenlop = request.getParameter("tenlop");
		
		if (tenlop.equals("")||request.getParameter("makhoa").equals("")||request.getParameter("siso").equals("")) {
			String errorMessage = "Không để trống các trường";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/ClassControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}
		int makhoa=Integer.parseInt(request.getParameter("makhoa"));
		int siso=Integer.parseInt(request.getParameter("siso"));
		//create a new class object
		Class theClass = new Class(tenlop, makhoa, siso);
		
		// add the student to the database
		classDbUtil.addClass(theClass);
		// send back to main page (the class list)
		listClasses(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không nhập chuỗi cho mã khoa và sĩ số";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/ClassControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}catch(MySQLIntegrityConstraintViolationException ex) {
			String errorMessage = "Khoa phải có trong danh sách khoa";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
            		= this.getServletContext().getRequestDispatcher("/ClassControllerServlet?command=SHOW");
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
	
	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClassManage/add-class-form.jsp");
		dispatcher.forward(request, response);
	}

}
