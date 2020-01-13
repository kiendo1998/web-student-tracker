package com.web.jdbc.faculitymanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class FaculityDbUtil {

	private DataSource dataSource;
	public FaculityDbUtil(DataSource theDataSource) {
		
	dataSource = theDataSource;
	}
	
	public List<Faculity> getFaculities() throws Exception{
		List<Faculity> faculities = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from faculity order by tenkhoa;";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int makhoa = myRs.getInt("makhoa");
			String tenkhoa = myRs.getString("tenkhoa");
			//create new student object
			Faculity tempFaculity = new Faculity(makhoa, tenkhoa);
			//add it to the list of sudents
			faculities.add(tempFaculity);
		}
		
			
			
			return faculities;
			
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

	public void addFaculity(Faculity theFaculity) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into faculity(Tenkhoa)"
			+ "value (?)"	;	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			//myStmt.setInt(1, theStudent.getMasv());
			myStmt.setString(1, theFaculity.getTenkhoa());

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

	public Faculity getFaculity(String theFaculityId) throws Exception {
		Faculity theFaculity = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int faculityId;
		try {
			//convert student id to int
			faculityId = Integer.parseInt(theFaculityId);
					
			//get connection to database
			myConn = dataSource.getConnection();
			//create sql to get selected student
			String sql = "select * from faculity where makhoa=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, faculityId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			if (myRs.next()) {
				int makhoa = myRs.getInt("makhoa");
				String tenkhoa = myRs.getString("tenkhoa");
				
				//use the studentId during construction
				theFaculity = new Faculity(makhoa, tenkhoa);
				
			}
			else {
				throw new Exception("không tìm thấy sinh viên có mã: " + faculityId);
			}
			
			
			
			return theFaculity;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
		
		
		
		
	}

	public void updateFaculity(Faculity theFaculity) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql="update faculity "
				+ "set tenkhoa=? "
				+ "where makhoa=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		
		myStmt.setString(1, theFaculity.getTenkhoa());
		myStmt.setInt(2, theFaculity.getMakhoa());
		//execute SQL statement
		myStmt.execute();
			}
	finally {
		//clean up JDBC objects
		close(myConn, myStmt, null);
		}
	}

	public void deleteFaculity(String theFaculityId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert student id to int
			int faculityId = Integer.parseInt(theFaculityId);
			//getconnection to database
			myConn = dataSource.getConnection();
			//create sql to delete student
			String sql = "delete from faculity where makhoa=?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, faculityId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
		}
		
		
	}

	public List<Faculity> searchFaculities(String theSearchName)  throws Exception {
        List<Faculity> faculities = new ArrayList<>();
        
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
                String sql = "select * from faculity where  lower(tenkhoa) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
          
                
            } else {
                // create sql to get all students
                String sql = "select * from faculity order by tenkhoa";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
            	int makhoa = myRs.getInt("makhoa");
				String tenkhoa = myRs.getString("tenkhoa");

                
                // create new student object
                Faculity tempFaculity = new Faculity(makhoa, tenkhoa);
                
                // add it to the list of students
                faculities.add(tempFaculity);            
            }
            
            return faculities;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
	
	
	
		
		

}
