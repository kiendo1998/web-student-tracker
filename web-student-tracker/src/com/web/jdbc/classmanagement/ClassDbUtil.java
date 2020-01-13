package com.web.jdbc.classmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ClassDbUtil {
	private DataSource dataSource;

	public ClassDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Class> searchClasses(String theSearchName) throws Exception{
    List<Class> classes = new ArrayList<>();
        
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
                // create sql to search for class by name
				  String sql = "select MaLop,TenLop,class.MaKhoa,TenKhoa,SiSo,LoaiLop" +
				  " from class inner join faculity on class.MaKhoa = faculity.MaKhoa " +
				  " where TenLop like ?";
				 
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                //myStmt.setString(2, theSearchNameLike);
                
            } else {
                // create sql to get all class
                String sql = "select MaLop,TenLop,class.MaKhoa,TenKhoa,SiSo,LoaiLop "
                		  +  "from class inner join faculity on class.MaKhoa = faculity.MaKhoa"
                		  + "order by MaLop";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int malop = myRs.getInt("MaLop");
                String tenlop = myRs.getString("TenLop");
                int makhoa  = myRs.getInt("MaKhoa");
                String tenkhoa = myRs.getString("TenKhoa");
                int siso = myRs.getInt("SiSo");
                boolean loailop = myRs.getBoolean("LoaiLop");
                
                // create new class object
                Class tempClass = new Class(malop, tenlop, makhoa, tenkhoa, siso, loailop);
                
                // add it to the list of classes
                classes.add(tempClass);            
            }
            
            return classes;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
	}
	public void deleteClass(String theClassId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert Class id to int
			int classId = Integer.parseInt(theClassId);
			//getconnection to database
			myConn = dataSource.getConnection();
			//create sql to delete Class
			String sql = "delete  from class "
					+ " where MaLop=?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, classId);
			
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

	public void updateClass(Class theClass) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql=" update class "
				+ "set tenlop=?,makhoa=?,siso=?,loailop=? "
				+ "where malop=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		myStmt.setString(1, theClass.getTenlop());
		myStmt.setInt(2, theClass.getMakhoa());
		myStmt.setInt(3, theClass.getSiso());
		myStmt.setBoolean(4, theClass.isLoailop());
		myStmt.setInt(5,theClass.getMalop());
		//execute SQL statement
		myStmt.execute();
			}
	finally {
		//clean up JDBC objects
		close(myConn, myStmt, null);
		}
	}
// for search feature
	public Class getClass(String theClassId) throws Exception {
Class theClass = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int classId;
		try {
			//convert subject id to int
			classId = Integer.parseInt(theClassId);
					
			//get connection to database
			myConn = dataSource.getConnection();
			//create sql to get selected class
			String sql = "select MaLop,TenLop,class.MaKhoa,TenKhoa,SiSo,LoaiLop"
					+ " from class inner join faculity on class.MaKhoa = faculity.MaKhoa"
					+ " where MaLop=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, classId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			if (myRs.next()) {
				int malop = myRs.getInt("MaLop");
				String tenlop = myRs.getString("TenLop");
				int makhoa = myRs.getInt("MaKhoa");
				String tenkhoa = myRs.getString("TenKhoa");
				int siso = myRs.getInt("SiSo");
				Boolean loailop = myRs.getBoolean("LoaiLop");
				
				
				//use the classId during construction
				theClass= new Class(malop, tenlop, makhoa, tenkhoa, siso, loailop);
				
			}
			else {
				throw new Exception("Không tìm thấy mã lớp : " + classId);
			}
			
			
			
			return theClass;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
		
		
		
	}

	public void addClass(Class theClass) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into class"
			+ "(tenlop, makhoa,siso,loailop) "
			+ "value (?, ?,?,?)"	;	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			myStmt.setString(1, theClass.getTenlop());
			myStmt.setInt(2, theClass.getMakhoa());
			myStmt.setInt(3, theClass.getSiso());
			myStmt.setBoolean(4, theClass.isLoailop());
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}

	public List<Class> getClasses() throws Exception{
List<Class> classes = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select MaLop,TenLop,class.MaKhoa,TenKhoa,LoaiLop,SiSo"
				+ " from class inner join faculity on class.MaKhoa = faculity.MaKhoa";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int malop = myRs.getInt("MaLop");
			String tenlop = myRs.getString("TenLop");
			int makhoa = myRs.getInt("MaKhoa");
			String tenkhoa = myRs.getString("TenKhoa");
			int siso = myRs.getInt("SiSo");
			boolean loailop = myRs.getBoolean("LoaiLop");
			//create new class object
			Class tempClass = new Class(malop, tenlop, makhoa, tenkhoa, siso, loailop);
			//add it to the list of class
			classes.add(tempClass);
		}
		
			
			
			return classes;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	


	
	


}
