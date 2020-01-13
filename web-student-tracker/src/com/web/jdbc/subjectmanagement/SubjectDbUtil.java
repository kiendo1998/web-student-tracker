package com.web.jdbc.subjectmanagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class SubjectDbUtil {
	private DataSource dataSource;

	public SubjectDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Subject> searchSubjects(String theSearchName) throws Exception{
    List<Subject> subjects = new ArrayList<>();
        
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
                // create sql to search for subjects by name
                String sql = "select * from subject where lower(tenmh) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                //myStmt.setString(2, theSearchNameLike);
                
            } else {
                // create sql to get all subjects
                String sql = "select * from subject order by tenmh";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int mamh = myRs.getInt("mamh");
                String tenmh = myRs.getString("tenmh");
                int sotc = myRs.getInt("sotc");
                
                // create new student object
                Subject tempSubject = new Subject(mamh, tenmh, sotc);
                
                // add it to the list of students
                subjects.add(tempSubject);            
            }
            
            return subjects;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
	}
	public void deleteSubject(String theSubjectId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert Subject id to int
			int subjectId = Integer.parseInt(theSubjectId);
			//getconnection to database
			myConn = dataSource.getConnection();
			//create sql to delete Subject
			String sql = "delete from subject where mamh=?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, subjectId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
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

	public void updateSubject(Subject theSubject) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql="update subject "
				+ "set tenmh=?,sotc=? "
				+ "where mamh=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		myStmt.setString(1, theSubject.getTenmh());
		myStmt.setInt(2, theSubject.getSotc());
		myStmt.setInt(3, theSubject.getMamh());
		//execute SQL statement
		myStmt.execute();
			}
	finally {
		//clean up JDBC objects
		close(myConn, myStmt, null);
		}
	}
// for search feature
	public Subject getSubject(String theSubjectId) throws Exception {
Subject theSubject = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int subjectId;
		try {
			//convert subject id to int
			subjectId = Integer.parseInt(theSubjectId);
					
			//get connection to database
			myConn = dataSource.getConnection();
			//create sql to get selected student
			String sql = "select * from subject where mamh=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, subjectId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			if (myRs.next()) {
				int mamh = myRs.getInt("mamh");
				String tenmh = myRs.getString("tenmh");
				int sotc = myRs.getInt("sotc");
				
				//use the subjectId during construction
				theSubject = new Subject(mamh, tenmh, sotc);
				
			}
			else {
				throw new Exception("Không tìm thấy môn học có mã " + subjectId);
			}
			
			
			
			return theSubject;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
		
		
		
	}

	public void addSubject(Subject theSubject) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into subject"
			+ "(tenmh, sotc) "
			+ "value (?, ?)"	;	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			myStmt.setString(1, theSubject.getTenmh());
			myStmt.setInt(2, theSubject.getSotc());
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}

	public List<Subject> getSubjects() throws Exception{
List<Subject> subjects = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from subject order by tenmh";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int mamh = myRs.getInt("mamh");
			String tenmh = myRs.getString("tenmh");
			int sotc = myRs.getInt("sotc");
			//create new student object
			Subject tempSubject = new Subject(mamh, tenmh, sotc);
			//add it to the list of sudents
			subjects.add(tempSubject);
		}
		
			
			
			return subjects;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}


	
	


}
