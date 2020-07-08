package com.web.jdbc.usermanagement.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.web.jdbc.foruser.Diem;
import com.web.jdbc.foruser.TKB;
import com.web.jdbc.studentscoremanagement.StudentScore;
import com.web.jdbc.usermanagement.bean.UserAccount;

public class UserUtil {

	private DataSource dataSource;
	public UserUtil(DataSource theDataSource) {
		
	dataSource = theDataSource;
	}
	
	public List<UserAccount> getAccounts() throws Exception{
		List<UserAccount> users = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from user ;";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			String username = myRs.getString("username");
			String password = myRs.getString("password");
			String role = myRs.getString("role");
			//create new student object
			UserAccount useraccount = new UserAccount(username, password,role);
			//add it to the list of sudents
			users.add(useraccount);
		}
		
			
			
			return users;
			
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
	
	public void updateUser(UserAccount user) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql="update user "
				+ "set username=?,password=? "
				+ "where username=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		
		myStmt.setString(1, user.getUserName());
		myStmt.setString(2, user.getPassword());
		myStmt.setString(3, user.getUserName0());
		//execute SQL statement
		myStmt.execute();
			}
	finally {
		//clean up JDBC objects
		close(myConn, myStmt, null);
		}
	}

	public List<TKB> getTKB(String theusername) throws Exception{	
		List<TKB> tkbs = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from user inner join sinhvien "
				+ "on user.masv=sinhvien.masv inner join diem "
				+ "on sinhvien.masv=diem.masv inner join dangky "
				+ "on diem.madk=dangky.madk inner join monhoc "
				+ "on dangky.mamh=monhoc.mamh "
				+ "where username=?;";
		myStmt = myConn.prepareStatement(sql);
		
		//set param
		myStmt.setString(1, theusername);
		//execute query
		myRs = myStmt.executeQuery();
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			String tenmh = myRs.getString("tenmh");

			String tkb = myRs.getString("lichhoc");

			//create new student object
			TKB tempTKB = new TKB(tenmh,tkb);
			//add it to the list of sudents
			tkbs.add(tempTKB);
		}
		
			
			
			return tkbs;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		
		
		
		
		
		
	}
	
	
	public List<TKB> getLD(String theusername) throws Exception{	
		List<TKB> tkbs = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from user inner join giangvien "
				+ "on user.magv=giangvien.magv inner join dangky "
				+ "on giangvien.magv=dangky.magv "
				+ "inner join monhoc "
				+ "on dangky.mamh=monhoc.mamh "
				+ "where username=?;";
		myStmt = myConn.prepareStatement(sql);
		
		//set param
		myStmt.setString(1, theusername);
		//execute query
		myRs = myStmt.executeQuery();
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			String tenmh = myRs.getString("tenmh");

			String tkb = myRs.getString("lichhoc");

			//create new student object
			TKB tempTKB = new TKB(tenmh,tkb);
			//add it to the list of sudents
			tkbs.add(tempTKB);
		}
		
			
			
			return tkbs;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}
	
	
	
	
	public List<Diem> getD(String theusername) throws Exception{	
		List<Diem> diems = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select *,case \r\n" + 
				"when (0.3*DiemQT + 0.7*DiemThi) < 4 then 0\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=4 and (0.3*DiemQT + 0.7*DiemThi) <= 4.9) then 1.0\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=5 and (0.3*DiemQT + 0.7*DiemThi) <= 5.4) then 1.5\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=5.5 and (0.3*DiemQT + 0.7*DiemThi) <= 6.4) then 2\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=6.5 and (0.3*DiemQT + 0.7*DiemThi) <= 6.9) then 2.5\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=7.0 and (0.3*DiemQT + 0.7*DiemThi) <= 7.9) then 3\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=8.0 and (0.3*DiemQT + 0.7*DiemThi) <=8.4)  then 3.5\r\n" + 
				"else 4\r\n" + 
				"end as diemhe4,\r\n" + 
				"case \r\n" + 
				"when (0.3*DiemQT + 0.7*DiemThi) < 4 then \"F\"\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=4 and (0.3*DiemQT + 0.7*DiemThi) <= 4.9) then \"D\"\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=5 and (0.3*DiemQT + 0.7*DiemThi) <= 5.4) then \"D+\"\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=5.5 and (0.3*DiemQT + 0.7*DiemThi) <= 6.4) then \"C\"\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=6.5 and (0.3*DiemQT + 0.7*DiemThi) <= 6.9) then \"C+\"\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=7.0 and (0.3*DiemQT + 0.7*DiemThi) <= 7.9) then \"B\"\r\n" + 
				"when ((0.3*DiemQT + 0.7*DiemThi) >=8.0 and (0.3*DiemQT + 0.7*DiemThi) <=8.4)  then \"B+\"\r\n" + 
				"else \"A\"\r\n" + 
				"end as diemchu from user inner join sinhvien "
				+ "on user.masv=sinhvien.masv inner join diem "
				+ "on sinhvien.masv=diem.masv "
				+ "inner join dangky "
				+ "on diem.madk=dangky.madk inner join monhoc "
				+ "on dangky.mamh=monhoc.mamh "
				+ "where username=?;";
		myStmt = myConn.prepareStatement(sql);
		
		//set param
		myStmt.setString(1, theusername);
		//execute query
		myRs = myStmt.executeQuery();
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int mamh = myRs.getInt("dangky.mamh");
			String tenmh = myRs.getString("tenmh");
			float dqt = myRs.getFloat("diemqt");
			float diemthi = myRs.getFloat("diemthi");
			String kyhoc = myRs.getString("kyhoc");
			float diemhe4 = myRs.getFloat("diemhe4");
			String diemchu = myRs.getString("diemchu");

			//create new student object
			Diem tempD = new Diem(mamh,tenmh,dqt,diemthi,kyhoc,diemhe4,diemchu);
			//add it to the list of sudents
			diems.add(tempD);
		}
		
			
			
			return diems;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}
	
		
		

}
