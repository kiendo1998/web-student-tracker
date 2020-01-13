package com.web.jdbc.scoremanagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class ScoreDbUtil {
	private DataSource dataSource;

	public ScoreDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

//	public List<Score> searchScores(String theSearchName) throws Exception{
//    List<Score> scores = new ArrayList<>();
//        
//        Connection myConn = null;
//        PreparedStatement myStmt = null;
//        ResultSet myRs = null;
//        
//        
//        try {
//            
//            // get connection to database
//            myConn = dataSource.getConnection();
//            
//            //
//            // only search by name if theSearchName is not empty
//            //
//            if (theSearchName != null && theSearchName.trim().length() > 0) {
//                // create sql to search for subjects by name
//                String sql = "select * from score where lower(masv) like ?";
//                // create prepared statement
//                myStmt = myConn.prepareStatement(sql);
//                // set params
//                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
//                myStmt.setString(1, theSearchNameLike);
//                //myStmt.setString(2, theSearchNameLike);
//                
//            } else {
//                // create sql to get all subjects
//                String sql = "select * from score order by masv";
//                // create prepared statement
//                myStmt = myConn.prepareStatement(sql);
//            }
//            
//            // execute statement
//            myRs = myStmt.executeQuery();
//            
//            // retrieve data from result set row
//            while (myRs.next()) {
//                
//                // retrieve data from result set row
//                int madiem = myRs.getInt("madiem");
//                int mabd = myRs.getInt("mabd");
//                int masv = myRs.getInt("masv");
//                
//                String tensv = myRs.getString("tensv");
//                String tenlop = myRs.getString("tenlop");
//                String tenmh = myRs.getString("tenmh");
//                float dqt = myRs.getFloat("diemqt");
//                float diemthi = myRs.getFloat("diemthi");
//                float diemkt = myRs.getFloat("diemkt");
//                // create new student object
//                Score tempScore = new Score(madiem, mabd, masv, tensv, tenlop, tenmh, dqt, diemthi, diemkt);
//                
//                // add it to the list of students
//                scores.add(tempScore);            
//            }
//            
//            return scores;
//        }
//        finally {
//            // clean up JDBC objects
//            close(myConn, myStmt, myRs);
//        }
//	}
//	public void deleteScore(String theScoreId) throws Exception {
//		Connection myConn = null;
//		PreparedStatement myStmt = null;
//		try {
//			//convert Subject id to int
//			int scoreId = Integer.parseInt(theScoreId);
//			//getconnection to database
//			myConn = dataSource.getConnection();
//			//create sql to delete Subject
//			String sql = "delete from score where id=?";
//			//prepare statement
//			myStmt = myConn.prepareStatement(sql);
//			
//			//set params
//			myStmt.setInt(1, scoreId);
//			
//			//execute sql statement
//			myStmt.execute();
//		}
//		finally {
//			//clean up JDBC code
//			close(myConn, myStmt, null);
//		}
//		
//		
//	}

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

//	public void updateScore(Score theScore) throws Exception{
//		Connection myConn = null;
//		PreparedStatement myStmt = null;
//		try {
//		//get db connection
//		myConn = dataSource.getConnection();
//		//create SQL update statement
//		String sql="update score inner join scoretable "
//				+ "on score.scoretableid=scoretable.scoretableid inner join class on scoretable.malop=class.malop"
//				+ "inner join subject on class.mamh=subject.mamh "
//				+ "set diemqt=?,diemthi=? "
//				+ "where masv=?,tenlop=?,tenmon=?";
//		//prepare statement
//		myStmt = myConn.prepareStatement(sql);
//		//set params
//		myStmt.setFloat(1, theScore.getDqt());
//		myStmt.setFloat(2, theScore.getDiemthi());
//		myStmt.setInt(3, theScore.getMasv());
//		myStmt.setString(4, theScore.getTenlop());
//
//		myStmt.setString(5, theScore.getTenmh());
//		//execute SQL statement
//		myStmt.execute();
//			}
//	finally {
//		//clean up JDBC objects
//		close(myConn, myStmt, null);
//		}
//	}
// for search feature
//	public Score getScore(String theScoreId) throws Exception {
//Score theScore = null;
//		
//		Connection myConn = null;
//		PreparedStatement myStmt = null;
//		ResultSet myRs = null;
//		int scoretId;
//		try {
//			//convert subject id to int
//			scoretId = Integer.parseInt(theScoreId);
//					
//			//get connection to database
//			myConn = dataSource.getConnection();
//			//create sql to get selected student
//			String sql = "select * from score where id=?";
//			//create prepared statement
//			myStmt = myConn.prepareStatement(sql);
//			
//			//set param
//			myStmt.setInt(1, scoretId);
//			//execute statement
//			myRs = myStmt.executeQuery();
//			//retrieve data from result set row
//			if (myRs.next()) {
//				int madiem = myRs.getInt("madiem");
//                int mabd = myRs.getInt("mabd");
//                int masv = myRs.getInt("masv");
//                
//                String tensv = myRs.getString("tensv");
//                String tenlop = myRs.getString("tenlop");
//                String tenmh = myRs.getString("tenmh");
//                float dqt = myRs.getFloat("diemqt");
//                float diemthi = myRs.getFloat("diemthi");
//                float diemkt = myRs.getFloat("diemkt");
//                // create new student object
//                theScore = new Score(madiem, mabd, masv, tensv, tenlop, tenmh, dqt, diemthi, diemkt);
////				//use the subjectId during construction
////				theSubject = new Subject(mamh, tenmh, sotc);
//				
//			}
//			else {
//				throw new Exception("Không tìm thấy môn học có mã " + scoretId);
//			}
//			
//			
//			
//			return theScore;
//		}
//		finally {
//			//clean up JDBC object
//			close(myConn, myStmt, myRs);
//		}
//		
//		
//		
//		
//	}

	public void addScore(Score theScore) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into student inner join score on student.masv=score.masv inner join scoretable "
					+ "on score.scoretableid=scoretable.scoretableid inner join class "
					+ "on scoretable.malop=class.malop "
					+ "inner join subject "
					+ "on scoretable.mamh=subject.mamh";	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			//myStmt.setInt(1, theScore.getMabd());
			myStmt.setInt(2, theScore.getMasv());
			myStmt.setFloat(3, theScore.getDqt());
			myStmt.setFloat(2, theScore.getDiemthi());
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}

	public List<Score> getScores() throws Exception{
List<Score> scores = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from student inner join score on student.masv=score.masv inner join scoretable "
				+ "on score.scoretableid=scoretable.scoretableid inner join class "
				+ "on scoretable.malop=class.malop "
				+ "inner join subject "
				+ "on scoretable.mamh=subject.mamh";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
            int masv = myRs.getInt("student.masv");
            String kyhoc = myRs.getString("kyhoc");
            String tensv = myRs.getString("tensv");
            String tenlop = myRs.getString("tenlop");
            String tenmh = myRs.getString("tenmh");
            float dqt = myRs.getFloat("diemqt");
            float diemthi = myRs.getFloat("diemthi");
            float diemkt = myRs.getFloat("diemkt");
            // create new student object
            Score tempScore = new Score(masv, tensv, tenlop, tenmh, dqt, diemthi, diemkt, kyhoc);
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


	
	


}
