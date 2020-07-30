package com.web.jdbc.usermanagement.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.web.jdbc.foruser.Diem;
import com.web.jdbc.foruser.TKB;
import com.web.jdbc.scoretablemanagement.Score;
import com.web.jdbc.scoretablemanagement.ScoreTable;
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
	
public void addScore(Score theScore) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "update diem set madk=?,masv=?,diemqt=?,diemthi=? where madk=? and masv=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, theScore.getScoretableid());
			myStmt.setInt(2, theScore.getMasv());
			myStmt.setFloat(3, theScore.getDqt());
			myStmt.setFloat(4, theScore.getDiemthi());
			myStmt.setInt(5, theScore.getScoretableid());
			myStmt.setInt(6, theScore.getMasv());
			
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}

	public ScoreTable getScoretable(String theScoretableId) throws Exception {
		ScoreTable theScoretable = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int scoretableId;
		try {
			//convert student id to int
			scoretableId = Integer.parseInt(theScoretableId);
					
			//get connection to database
			myConn = dataSource.getConnection();
			//create sql to get selected student
			String sql = "select * from dangky where madk=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			//set param
			myStmt.setInt(1, scoretableId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			if (myRs.next()) {
				int scoretableid = myRs.getInt("madk");
				int mamh = myRs.getInt("mamh");
				String kyhoc = myRs.getString("kyhoc");
				//use the studentId during construction
				theScoretable = new ScoreTable(scoretableid, mamh, kyhoc);
			}
			else {
				throw new Exception("không tìm thấy bảng điểm  có mã: " + scoretableId);
			}	
			return theScoretable;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
	}
	public List<Score> getScores(String theScoretableId) throws Exception{	
		List<Score> scores = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int scoretableId;
		try {
			scoretableId = Integer.parseInt(theScoretableId);
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select id,masv,diemqt,diemthi from diem where madk=?";
		
		myStmt = myConn.prepareStatement(sql);
		
		//set param
		myStmt.setInt(1, scoretableId);
		//execute query
		myRs = myStmt.executeQuery();
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int masv = myRs.getInt("masv");
			int scoreid = myRs.getInt("id");
			float dqt = myRs.getFloat("diemqt");
			float diemthi = myRs.getFloat("diemthi");
			//create new student object
			Score tempScore = new Score(masv, dqt, diemthi,scoreid);
			//add it to the list of sudents
			scores.add(tempScore);
		}	
			return scores;	
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}
	public List<ScoreTable> getScoretables(String username) throws Exception{
		List<ScoreTable> scoretables = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
		myConn = dataSource.getConnection();
		String sql = "select * from monhoc inner join dangky on monhoc.mamh=dangky.mamh inner join user on dangky.magv=user.magv where username=?;";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, username);
		myRs = myStmt.executeQuery();
		while (myRs.next()) {

			int scoretableid = myRs.getInt("madk");
			int mamh = myRs.getInt("monhoc.MaMH");
			String tenmh = myRs.getString("tenmh");
			String kyhoc = myRs.getString("KyHoc");
			ScoreTable tempScoretable = new ScoreTable(scoretableid, mamh, kyhoc, tenmh);
			scoretables.add(tempScoretable);
		}
		
			
			
			return scoretables;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}
	public List<ScoreTable> getRegistedScoretables(String username) throws Exception{
		List<ScoreTable> scoretables = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
		myConn = dataSource.getConnection();
		String sql = "select * from monhoc inner join dangky on monhoc.mamh=dangky.mamh inner join diem on dangky.madk=diem.madk inner join  user on diem.masv=user.masv where username=?;";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, username);
		myRs = myStmt.executeQuery();
		while (myRs.next()) {

			int scoretableid = myRs.getInt("madk");
			int mamh = myRs.getInt("monhoc.MaMH");
			String tenmh = myRs.getString("tenmh");
			String kyhoc = myRs.getString("KyHoc");
			ScoreTable tempScoretable = new ScoreTable(scoretableid, mamh, kyhoc, tenmh);
			scoretables.add(tempScoretable);
		}
		
			
			
			return scoretables;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		
	}
	
	public List<ScoreTable> getScoretablesToRegister(String username) throws Exception{
		List<ScoreTable> scoretables = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
		myConn = dataSource.getConnection();
		String sql = "select * from monhoc inner join  dangky on monhoc.mamh=dangky.mamh where dangky.madk not in (select dangky.madk from dangky left join diem on dangky.madk=diem.madk inner join  user on diem.masv=user.masv where  username=? group by dangky.madk) group by madk;";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, username);
		myRs = myStmt.executeQuery();
		while (myRs.next()) {

			int scoretableid = myRs.getInt("madk");
			int mamh = myRs.getInt("monhoc.MaMH");
			String tenmh = myRs.getString("tenmh");
			String kyhoc = myRs.getString("KyHoc");
			ScoreTable tempScoretable = new ScoreTable(scoretableid, mamh, kyhoc, tenmh);
			scoretables.add(tempScoretable);
		}
			return scoretables;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	
public void registerSubject(Score theScore) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into diem(madk,masv)"
			+ "value (?, ?)"	;	
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, theScore.getScoretableid());
			myStmt.setInt(2, theScore.getMasv());
			
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}

public int getMaSV(String username) throws SQLException {
	int masv = 0;
	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	try {
	myConn = dataSource.getConnection();
	String sql = "select * from user where username=?;";
	myStmt = myConn.prepareStatement(sql);
	myStmt.setString(1, username);
	myRs = myStmt.executeQuery();
	while (myRs.next()) {

		masv = myRs.getInt("masv");
	}
		return masv;
		
	}
	finally {
		//close JDBC objects
		close(myConn, myStmt, myRs);
	}
}

public List<ScoreTable> searchScoretables(String theSearchName)  throws Exception {
        List<ScoreTable> scoretables = new ArrayList<>();
        
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
                String sql = "select * from monhoc inner join "
                		+ "dangky on monhoc.mamh=dangky.mamh "
                		+ "where lower(tenmh) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
          
                
            } else {
                // create sql to get all students
                String sql = "select * from monhoc inner join dangky on monhoc.mamh=dangky.mamh "
        				+ " order by madk;";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
            	int scoretableid = myRs.getInt("madk");
    			int mamh = myRs.getInt("MaMH");
    			String tenmh = myRs.getString("tenmh");
    			String kyhoc = myRs.getString("KyHoc");
    			//create new student object
    			ScoreTable tempScoretable = new ScoreTable(scoretableid, mamh, kyhoc, tenmh);
                // add it to the list of students
                scoretables.add(tempScoretable);            
            }
            
            return scoretables;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
		

}
