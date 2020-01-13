package com.web.jdbc.scoretablemanagement.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ScoreTableDbUtil {

	private DataSource dataSource;
	public ScoreTableDbUtil(DataSource theDataSource) {
		
	dataSource = theDataSource;
	}
	
	public List<ScoreTable> getScoretables() throws Exception{
		List<ScoreTable> scoretables = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from class inner join scoretable on class.malop=scoretable.malop "
				+ "inner join subject on scoretable.mamh=subject.mamh  order by scoretableid;";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int scoretableid = myRs.getInt("scoretableid");
			int mamh = myRs.getInt("MaMH");
			String tenmh = myRs.getString("tenmh");
			
			int malop = myRs.getInt("MaLop");
			String tenlop = myRs.getString("tenlop");
			String kyhoc = myRs.getString("KyHoc");
			//create new student object
			ScoreTable tempScoretable = new ScoreTable(scoretableid, mamh, malop, kyhoc, tenmh, tenlop);
			//add it to the list of sudents
			scoretables.add(tempScoretable);
		}
		
			
			
			return scoretables;
			
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
				myConn.close(); 
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addScoretable(ScoreTable theScoretable) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into scoretable(mamh,malop,kyhoc) "
			+ "value (?, ?, ?)"	;	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			//myStmt.setInt(1, theStudent.getMasv());
			myStmt.setInt(1, theScoretable.getMamh());
			myStmt.setInt(2, theScoretable.getMalop());
			myStmt.setString(3, theScoretable.getKyhoc());

			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}
//	public void addScore(Score theScore) throws Exception {
//		
//		Connection myConn = null;
//		PreparedStatement myStmt = null;
//		try {
//			//get db connection
//			myConn = dataSource.getConnection();
//			//create sql for insert
//			String sql = "insert into score(masv,malop,kyhoc)"
//			+ "value (?, ?, ?)"	;	
//			myStmt = myConn.prepareStatement(sql);
//			//set the param values for the student
//			//myStmt.setInt(1, theStudent.getMasv());
//			myStmt.setInt(1, theScore.getMaMH());
//			myStmt.setInt(2, theScore.getMaLop());
//			myStmt.setString(3, theScore.getKyHoc());
//
//			//execute sql insert
//			myStmt.execute();
//		}
//		finally {
//		
//		//clean up JDBC object
//			close(myConn, myStmt, null);
//		}
//		
//		
//	}
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
			String sql = "select * from subject inner join scoretable "
					+ "on subject.mamh=scoretable.mamh inner join class on scoretable.malop=class.malop "
					+ " where scoretableid=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, scoretableId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			
			if (myRs.next()) {
				int scoretableid = myRs.getInt("scoretableid");
				String tenmh = myRs.getString("tenmh");
				String tenlop = myRs.getString("tenlop");
				String kyhoc = myRs.getString("kyhoc");
				//use the studentId during construction
				theScoretable = new ScoreTable(scoretableid,kyhoc,tenmh,tenlop);
				
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
	public Score getScore(String theScoretableId) throws Exception {
		Score theScore = null;
		
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
			String sql = "select * from score where scoretableid=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, scoretableId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			
			if (myRs.next()) {
				int scoretableid = myRs.getInt("scoretableid");
				int scoreid = myRs.getInt("scoreid");
				int masv = myRs.getInt("masv");
				float dqt = myRs.getFloat("diemqt");
				float diemthi = myRs.getFloat("diemthi");
				float diemkt = myRs.getFloat("diemkt");
				//use the studentId during construction
				theScore = new Score(scoretableid, scoreid, masv, dqt, diemthi, diemkt);
				
			}
			else {
				throw new Exception("Bảng điểm có mã " + scoretableId+" chưa có dữ liệu vì chưa được nhập điểm, xin mời nhập điểm trước !");
			}
			
			
			return theScore;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
		
		
		
		
	}

	public void updateScoretable(ScoreTable theScoretable) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql="update scoretable "
				+ "set MaMH=?,malop=?, kyhoc=? "
				+ "where scoretableid=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		
		myStmt.setInt(1, theScoretable.getMamh());
		myStmt.setInt(2, theScoretable.getMalop());
		myStmt.setString(3, theScoretable.getKyhoc());
		myStmt.setInt(4, theScoretable.getScoretableid());
		//execute SQL statement
		myStmt.execute();
			}
	finally {
		//clean up JDBC objects
		close(myConn, myStmt, null);
		}
	}

	public void deleteScoretable(String theScoretableId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert student id to int
			int scoretableId = Integer.parseInt(theScoretableId);
			//getconnection to database
			myConn = dataSource.getConnection();
			//create sql to delete student
			String sql = "delete from scoretable where scoretableid=?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, scoretableId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
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
                String sql = "select * from class inner join "
                		+ "scoretable on class.malop=scoretable.malop "
                		+ "inner join subject on scoretable.mamh=subject.mamh "
                		+ "where lower(tenlop) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
          
                
            } else {
                // create sql to get all students
                String sql = "select * from class inner join scoretable on class.malop=scoretable.malop "
        				+ "inner join subject on scoretable.mamh=subject.mamh  order by scoretableid;";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
            	int scoretableid = myRs.getInt("scoretableid");
    			int mamh = myRs.getInt("MaMH");
    			String tenmh = myRs.getString("tenmh");
    			
    			int malop = myRs.getInt("MaLop");
    			String tenlop = myRs.getString("tenlop");
    			String kyhoc = myRs.getString("KyHoc");
    			//create new student object
    			ScoreTable tempScoretable = new ScoreTable(scoretableid, mamh, malop, kyhoc, tenmh, tenlop);
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
	public List<Score> getScores(String theScoretableId) throws Exception{
//		public Score getScoretable(String theScoretableId) throws Exception {
//			Score theScoretable = null;
//			
//			Connection myConn = null;
//			PreparedStatement myStmt = null;
//			ResultSet myRs = null;
//			int scoretableId;
//			try {
//				//convert student id to int
//				scoretableId = Integer.parseInt(theScoretableId);
//						
//				//get connection to database
//				myConn = dataSource.getConnection();
//				//create sql to get selected student
//				String sql = "select * from score where scoretableid=?";
//				//create prepared statement
//				myStmt = myConn.prepareStatement(sql);
//				
//				//set param
//				myStmt.setInt(1, scoretableId);
//				//execute statement
//				myRs = myStmt.executeQuery();
//				//retrieve data from result set row
//				
//				if (myRs.next()) {
//					int scoretableid = myRs.getInt("scoretableid");
//					int scoreid = myRs.getInt("scoreid");
//					int masv = myRs.getInt("masv");
//					float dqt = myRs.getFloat("diemqt");
//					float diemthi = myRs.getFloat("diemthi");
//					//use the studentId during construction
//					theScoretable = new Score(masv, scoretableid, dqt, diemthi, scoreid);
//					
//				}
//				else {
//					throw new Exception("không tìm thấy bảng điểm  có mã: " + scoretableId);
//				}
//				
//				
//				return theScoretable;
//			}
//			finally {
//				//clean up JDBC object
//				close(myConn, myStmt, myRs);
//			}
//			
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
		String sql = "select scoreid,score.masv,tensv,diemqt,diemthi,diemkt from score inner join student on score.masv=student.masv where scoretableid=?";
		
		myStmt = myConn.prepareStatement(sql);
		
		//set param
		myStmt.setInt(1, scoretableId);
		//execute query
		myRs = myStmt.executeQuery();
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int masv = myRs.getInt("score.masv");
			String tensv = myRs.getString("tensv");
			int scoreid = myRs.getInt("scoreid");
			float dqt = myRs.getFloat("diemqt");
			float diemthi = myRs.getFloat("diemthi");
			float diemkt = myRs.getFloat("diemkt");
			//create new student object
			Score tempScore = new Score(masv,tensv, dqt, diemthi, diemkt,scoreid);
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
	
	
public void addScore(Score theScore) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into score(scoretableid,masv,diemqt,diemthi,diemkt)"
			+ "value (?, ?, ?, ?,?)"	;	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			//myStmt.setInt(1, theStudent.getMasv());
			myStmt.setInt(1, theScore.getScoretableid());
			myStmt.setInt(2, theScore.getMasv());
			myStmt.setFloat(3, theScore.getDqt());
			myStmt.setFloat(4, theScore.getDiemthi());
			myStmt.setFloat(5, (float)(theScore.getDiemthi()*0.7+0.3*theScore.getDqt()));
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}
	
		
		

}
