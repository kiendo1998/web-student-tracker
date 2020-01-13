package com.web.jdbc.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;
	public StudentDbUtil(DataSource theDataSource) {
		
	dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from student order by tensv;";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int masv = myRs.getInt("masv");
			String tensv = myRs.getString("tensv");
			String ngaysinh = myRs.getString("ngaysinh");
			String gioitinh = myRs.getString("gioitinh");
			String diachi = myRs.getString("diachi");
			int sotin = myRs.getInt("sotin");
			float diemtichluy = myRs.getFloat("diemtichluy");
			//create new student object
			Student tempStudent = new Student(masv, tensv, ngaysinh, gioitinh, diachi, sotin, diemtichluy);
			//add it to the list of sudents
			students.add(tempStudent);
		}
		
			
			
			return students;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs !=null) {
				myRs.close();
			}
			if (myStmt !=null) {
				myStmt.close();
			}
			if (myConn !=null) {
				myConn.close();  // doesn't really close it....just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into student(TenSV,NgaySinh,GioiTinh,DiaChi)"
			+ "value (?, ?, ?,?)"	;	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			//myStmt.setInt(1, theStudent.getMasv());
			myStmt.setString(1, theStudent.getTensv());
			myStmt.setString(2, theStudent.getNgaysinh());
			myStmt.setString(3, theStudent.getGioitinh());
			myStmt.setString(4, theStudent.getDiachi());
//			myStmt.setInt(5, theStudent.getSotin());
//			myStmt.setFloat(6, theStudent.getDiemtichluy());
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}

	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		try {
			//convert student id to int
			studentId = Integer.parseInt(theStudentId);
					
			//get connection to database
			myConn = dataSource.getConnection();
			//create sql to get selected student
			String sql = "select * from student where masv=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, studentId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			if (myRs.next()) {
				int masv = myRs.getInt("masv");
				String tensv = myRs.getString("tensv");
				String ngaysinh = myRs.getString("ngaysinh");
				String gioitinh = myRs.getString("gioitinh");
				String diachi = myRs.getString("diachi");
				int sotin = myRs.getInt("sotin");
				float diemtichluy = myRs.getFloat("diemtichluy");
				
				//use the studentId during construction
				theStudent = new Student(masv, tensv, ngaysinh, gioitinh, diachi, sotin, diemtichluy);
				
			}
			else {
				throw new Exception("không tìm thấy sinh viên có mã: " + studentId);
			}
			
			
			
			return theStudent;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
		
		
		
		
	}

	public void updateStudent(Student theStudent) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql="update student "
				+ "set tensv=?,ngaysinh=?, gioitinh=?,diachi=?,sotin=?,diemtichluy=? "
				+ "where masv=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		
		myStmt.setString(1, theStudent.getTensv());
		myStmt.setString(2, theStudent.getNgaysinh());
		myStmt.setString(3, theStudent.getGioitinh());
		myStmt.setString(4, theStudent.getDiachi());
		myStmt.setInt(5, theStudent.getSotin());
		myStmt.setFloat(6, theStudent.getDiemtichluy());
		myStmt.setInt(7, theStudent.getMasv());
		//execute SQL statement
		myStmt.execute();
			}
	finally {
		//clean up JDBC objects
		close(myConn, myStmt, null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			//getconnection to database
			myConn = dataSource.getConnection();
			//create sql to delete student
			String sql = "delete from student where masv=?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, studentId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
		}
		
		
	}

	public List<Student> searchStudents(String theSearchName)  throws Exception {
        List<Student> students = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        
        try {
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            //
            // only search by name if theSearchName is not empty
            //
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                // create sql to search for students by name
                String sql = "select * from student where  lower(tensv) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
          
                
            } else {
                // create sql to get all students
                String sql = "select * from student order by tensv";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
            	int masv = myRs.getInt("masv");
				String tensv = myRs.getString("tensv");
				String ngaysinh = myRs.getString("ngaysinh");
				String gioitinh = myRs.getString("gioitinh");
				String diachi = myRs.getString("diachi");
				int sotin = myRs.getInt("sotin");
				float diemtichluy = myRs.getFloat("diemtichluy");
                
                // create new student object
                Student tempStudent = new Student(masv, tensv, ngaysinh, gioitinh, diachi, sotin, diemtichluy);
                
                // add it to the list of students
                students.add(tempStudent);            
            }
            
            return students;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
	
	
	
		
		

}
