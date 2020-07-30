package com.web.jdbc.studentmanagement;


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


/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our student db util ... and pass in the conn pool / datasource
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
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
			case "login":
				checkAccount(request, response);
				break;
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request, response);
			    break;
			case "DELETE":
				deleteStudent(request, response);
				break;
			case "SEARCH":
                searchStudents(request, response);
                break;
			case "SHOW":
				showAdd(request, response);
                break;
			
			}
		// list the sudents ... in MVC fashion
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<Student> students = studentDbUtil.searchStudents(theSearchName);
        
        // add students to the request
        request.setAttribute("STUDENT_LIST", students);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("StudentManage/list-students.jsp");
        dispatcher.forward(request, response);
    }

	
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
	
		// read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		//delete student from database
		studentDbUtil.deleteStudent(theStudentId);
		//send them back to "list students" page
		listStudents(request, response);
		
		
		
	}





	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		try {
		//read student infor from form data
		int masv=Integer.parseInt(request.getParameter("studentId"));
		String tensv = request.getParameter("tensv");
		String ngaysinh = request.getParameter("ngaysinh");
		String gioitinh = request.getParameter("gioitinh");
		String diachi = request.getParameter("diachi");
		int malop=Integer.parseInt(request.getParameter("malop"));
		String sdt = request.getParameter("sdt");
		
		//create a new student object
		Student theStudent = new Student( masv,tensv, ngaysinh, gioitinh, diachi, malop, sdt);
		if (tensv.equals("")||ngaysinh.equals("")||gioitinh.equals("")||diachi.equals("")||sdt.equals("")) {
			String errorMessage = "Không để trống các trường";
            request.setAttribute("errorMessage", errorMessage);
            loadStudent(request, response);
            return;
		}
		//perform update on database
		studentDbUtil.updateStudent(theStudent);
		
		//send them back to the "list students" page
		listStudents(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không để trống hoặc nhập chuỗi cho mã lớp";
            request.setAttribute("errorMessage", errorMessage);
            loadStudent(request, response);
            return;
		}catch(SQLException e) {
			String errorMessage = "Nhập ngày sinh dạng yyyy-mm-dd";
            request.setAttribute("errorMessage", errorMessage);
            loadStudent(request, response);
            return;
		}catch(Exception e) {
			String errorMessage = "Nhập thông tin không đúng";
            request.setAttribute("errorMessage", errorMessage);
            loadStudent(request, response);
            return;
		}
	}





	private void loadStudent(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		
		//read student id from form data
		String theStudentId = request.getParameter("studentId");
		//get student from database (db util)
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		//place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("StudentManage/update-student-form.jsp");
		dispatcher.forward(request, response);
	}





	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
		// read student infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
		String tensv = request.getParameter("tensv");
		String ngaysinh = request.getParameter("ngaysinh");
		//boolean gioitinh = Boolean.parseBoolean(request.getParameter("gioitinh"));
		String gioitinh =request.getParameter("gioitinh");
		String diachi = request.getParameter("diachi");
		int malop=Integer.parseInt(request.getParameter("malop"));
		String sdt = request.getParameter("sdt");
		//create a new student object
		Student theStudent = new Student(tensv, ngaysinh, gioitinh, diachi,malop,sdt);
		
		// add the student to the database
		
		// send back to main page (the student list)
		
		if (tensv.equals("")||ngaysinh.equals("")||gioitinh.equals("")||diachi.equals("")||sdt.equals("")) {
			String errorMessage = "Không để trống các trường";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/StudentControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}
		studentDbUtil.addStudent(theStudent);
		listStudents(request, response);
		}catch(NumberFormatException ex) {
			String errorMessage = "Không để trống hoặc nhập chuỗi cho mã lớp";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/StudentControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}catch(SQLException e) {
			String errorMessage = "Nhập ngày sinh dạng yyyy-mm-dd";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/StudentControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}catch(Exception e) {
			String errorMessage = "Nhập thông tin không đúng";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/StudentControllerServlet?command=SHOW");
            dispatcher.forward(request, response);
            return;
		}
	}





	private void listStudents(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//get students from db util
		List<Student> students = studentDbUtil.getStudents();
		//add students to the request
		request.setAttribute("STUDENT_LIST", students);
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentManage/list-students.jsp");
		dispatcher.forward(request, response);
	}
	private void checkAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userName = request.getParameter("username");
		String userPass = request.getParameter("userpass");
		if (userName.equals("admin") && userPass.equals("admin")) {
			listStudents(request, response);
		}else {
			
			String warning = "Tài khoản hoặc mật khẩu sai!";
			request.setAttribute("Warning", warning);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	
	}
	
	
	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentManage/add-student-form.jsp");
		dispatcher.forward(request, response);
	}
	
	

}
