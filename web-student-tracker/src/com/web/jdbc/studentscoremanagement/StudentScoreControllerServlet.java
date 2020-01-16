package com.web.jdbc.studentscoremanagement;


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


/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentScoreControllerServlet")
public class StudentScoreControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentScoreDbUtil studentDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our student db util ... and pass in the conn pool / datasource
		try {
			studentDbUtil = new StudentScoreDbUtil(dataSource);
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
				listStudents(request, response);
				break;
			case "LIST1":
				listScores(request, response);
				break;	
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "LOAD1":
				loadScore(request,response);
				break;
			case "UPDATE1":
				updateScore(request, response);
			    break;
			case "DELETE":
				deleteStudent(request, response);
				break;
			case "DELETE1":
				deleteScore(request, response);
				break;
			case "SEARCH":
                searchStudents(request, response);
                break;
			
			}
		// list the sudents ... in MVC fashion
		listStudents(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}


	private void updateScore(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
				
				//read student infor from form data
				int masv=Integer.parseInt(request.getParameter("studentId"));
				int scoreid=Integer.parseInt(request.getParameter("scoreId"));
//				String tensv = request.getParameter("tensv");
//				String ngaysinh = request.getParameter("ngaysinh");
//				String gioitinh = request.getParameter("gioitinh");
//				String diachi = request.getParameter("diachi");
//				int sotin=Integer.parseInt(request.getParameter("sotin"));
				float dqt=Float.parseFloat(request.getParameter("dqt"));
				float diemthi=Float.parseFloat(request.getParameter("diemthi"));
				//create a new student object
				StudentScore theStudent = new StudentScore(masv,dqt, diemthi,scoreid);
				
				//perform update on database
				studentDbUtil.updateScore(theStudent);
				
				//send them abck to the "list students" page
				loadStudent(request, response);
			}
	private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<StudentScore> students = studentDbUtil.searchStudents(theSearchName);
        
        // add students to the request
        request.setAttribute("STUDENT_LIST", students);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("StudentScoreManage/list-students.jsp");
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
	private void deleteScore(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			
				// read student id from form data
				String theStudentId = request.getParameter("studentId");
				String theScoreId = request.getParameter("scoreId");
				//delete student from database
				studentDbUtil.deleteScore(theStudentId,theScoreId);
				//send them back to "list students" page
				loadStudent(request, response);
				
				
				
			}





//	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
//	throws Exception {
//		
//		//read student infor from form data
//		int masv=Integer.parseInt(request.getParameter("studentId"));
//		String tensv = request.getParameter("tensv");
//		String ngaysinh = request.getParameter("ngaysinh");
//		String gioitinh = request.getParameter("gioitinh");
//		String diachi = request.getParameter("diachi");
//		int sotin=Integer.parseInt(request.getParameter("sotin"));
//		float diemtichluy=Float.parseFloat(request.getParameter("diemtichluy"));
//		
//		//create a new student object
//		Student theStudent = new Student( masv,tensv, ngaysinh, gioitinh, diachi, sotin, diemtichluy);
//		
//		//perform update on database
//		studentDbUtil.updateStudent(theStudent);
//		
//		//send them abck to the "list students" page
//		listStudents(request, response);
//	}
//




	private void loadStudent(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		
		//read student id from form data
		String theStudentId = request.getParameter("studentId");
		//get student from database (db util)
		StudentScore theStudent = studentDbUtil.getStudent(theStudentId);
		//place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		//send to jsp page: update-student-form.jsp
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("StudentScoreManage/update-student-form.jsp");
//		dispatcher.forward(request, response);
		listScores(request, response);
	}
	private void loadScore(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
				
				//read student id from form data
				String theStudentId = request.getParameter("studentId");
				String theScoreId = request.getParameter("scoreId");
				//get student from database (db util)
				StudentScore theStudent = studentDbUtil.getScore(theStudentId,theScoreId);
				//place student in the request attribute
				request.setAttribute("THE_STUDENT1", theStudent);
				//send to jsp page: update-student-form.jsp
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("StudentScoreManage/update-score-form.jsp");
				dispatcher.forward(request, response);
//				listScores(request, response);
			}





	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// read student infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
		String tensv = request.getParameter("tensv");
		String ngaysinh = request.getParameter("ngaysinh");
		//boolean gioitinh = Boolean.parseBoolean(request.getParameter("gioitinh"));
		String gioitinh =request.getParameter("gioitinh");
		String diachi = request.getParameter("diachi");
//		int sotin=Integer.parseInt(request.getParameter("sotin"));
//		float diemtichluy=Float.parseFloat(request.getParameter("diemtichluy"));
		
		//create a new student object
		StudentScore theStudent = new StudentScore(tensv, ngaysinh, gioitinh, diachi);
		
		// add the student to the database
		studentDbUtil.addStudent(theStudent);
		// send back to main page (the student list)
		listStudents(request, response);
	}





	private void listStudents(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//get students from db util
		List<StudentScore> students = studentDbUtil.getStudents();
		//add students to the request
		request.setAttribute("STUDENT_LIST", students);
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentScoreManage/list-students.jsp");
		dispatcher.forward(request, response);
	}
	private void listScores(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String theStudentId = request.getParameter("studentId");
				//get students from db util
				List<StudentScore> students = studentDbUtil.getScores(theStudentId);
				//add students to the request
				request.setAttribute("SCORE_LIST", students);
				//send to jsp page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentScoreManage/update-student-form.jsp");
				dispatcher.forward(request, response);
			}
	
	

}
