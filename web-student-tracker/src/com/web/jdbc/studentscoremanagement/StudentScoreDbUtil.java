package com.web.jdbc.studentscoremanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.web.jdbc.studentscoremanagement.StudentScore;

public class StudentScoreDbUtil {

	private DataSource dataSource;
	public StudentScoreDbUtil(DataSource theDataSource) {
		
	dataSource = theDataSource;
	}
	
	public List<StudentScore> getStudents() throws Exception{
		List<StudentScore> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select * from sinhvien order by tensv;";
		
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
			int malop = myRs.getInt("malop");
			String sdt = myRs.getString("sdt");
			//create new student object
			StudentScore tempStudent = new StudentScore(masv, tensv, ngaysinh, gioitinh, diachi, malop, sdt);
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
	public List<StudentScore> getScores(String theStudentId) throws Exception{		
//List<Score> scores = new ArrayList<>();
//		
//		Connection myConn = null;
//		PreparedStatement myStmt = null;
//		ResultSet myRs = null;
//		int scoretableId;
//		try {
//			scoretableId = Integer.parseInt(theScoretableId);
//		//get a connection
//		myConn = dataSource.getConnection();
//		//create sql statement
//		String sql = "select scoreid,masv,diemqt,diemthi,diemkt from score where scoretableid=?";
//		
//		myStmt = myConn.prepareStatement(sql);
//		
//		//set param
//		myStmt.setInt(1, scoretableId);
//		//execute query
//		myRs = myStmt.executeQuery();
//		//process result set
//		while (myRs.next()) {
//			//retrieve data from result set row
//			int masv = myRs.getInt("masv");
//			int scoreid = myRs.getInt("scoreid");
//			float dqt = myRs.getFloat("diemqt");
//			float diemthi = myRs.getFloat("diemthi");
//			float diemkt = myRs.getFloat("diemkt");
//			//create new student object
//			Score tempScore = new Score(masv, dqt, diemthi, diemkt,scoreid);
//			//add it to the list of sudents
//			scores.add(tempScore);
//		}
//		
//			
//			
//			return scores;
//			
//		}
//		finally {
//			//close JDBC objects
//			close(myConn, myStmt, myRs);
//		}
//		
//	}
		
		List<StudentScore> students = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		try {
			studentId = Integer.parseInt(theStudentId);
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
				"end as diemchu from monhoc inner join dangky "
				+ "on monhoc.mamh=dangky.mamh inner join diem "
				+ "on dangky.madk=diem.madk inner join sinhvien on diem.masv=sinhvien.masv  where sinhvien.masv=?;";
		

		myStmt = myConn.prepareStatement(sql);
		
		//set param
		myStmt.setInt(1, studentId);
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
			int id = myRs.getInt("id");
			int masv = myRs.getInt("masv");
			float diemhe4 = myRs.getFloat("diemhe4");
			String diemchu = myRs.getString("diemchu");
			//create new student object
			StudentScore tempStudent = new StudentScore(masv,mamh, tenmh, dqt, diemthi, kyhoc,id,diemhe4,diemchu);
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

	public void addStudent(StudentScore theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into sinhvien(TenSV,NgaySinh,GioiTinh,DiaChi) "
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

	public StudentScore getStudent(String theStudentId) throws Exception {
		StudentScore theStudent = null;
		
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
			String sql = "select * from sinhvien where masv=?";
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
				int malop = myRs.getInt("malop");
				String sdt = myRs.getString("sdt");
				
				//use the studentId during construction
				theStudent = new StudentScore(masv, tensv, ngaysinh, gioitinh, diachi, malop, sdt);
				
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

	
	public StudentScore getScore(String theStudentId,String theScoreId) throws Exception {
		StudentScore theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		int scoreId;
		try {
			//convert student id to int
			studentId = Integer.parseInt(theStudentId);
			scoreId = Integer.parseInt(theScoreId);		
			//get connection to database
			myConn = dataSource.getConnection();
			//create sql to get selected student
			String sql = "select * from diem inner join dangky "
					+ "on diem.madk=dangky.madk "
					+ "where diem.masv=? and id=?;";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, studentId);
			myStmt.setInt(2, scoreId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			if (myRs.next()) {
				int masv = myRs.getInt("masv");
				int id = myRs.getInt("id");
				float dqt = myRs.getFloat("diemqt");
				float diemthi = myRs.getFloat("diemthi");
				//use the studentId during construction
				theStudent = new StudentScore(masv, dqt, diemthi, id);
				
			}
			else {
				throw new Exception("không tìm thấy điểm có mã: " + scoreId);
			}
			
			
			
			return theStudent;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
		
		
		
		
	}
	
	
	public void updateScore(StudentScore theStudent) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql="update diem "
				+ "set diemqt=?,diemthi=? "
				+ "where id=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		myStmt.setFloat(1, theStudent.getDqt());
		myStmt.setFloat(2, theStudent.getDiemthi());
		myStmt.setInt(3, theStudent.getId());
		
//		myStmt.setString(1, theStudent.getTensv());
//		myStmt.setString(2, theStudent.getNgaysinh());
//		myStmt.setString(3, theStudent.getGioitinh());
//		myStmt.setString(4, theStudent.getDiachi());
//		myStmt.setInt(5, theStudent.getSotin());
//		myStmt.setFloat(6, theStudent.getDiemtichluy());
//		myStmt.setInt(7, theStudent.getMasv());
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
			String sql = "delete from sinhvien where masv=?";
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
	public void deleteScore(String theStudentId,String theScoreId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			int scoreId = Integer.parseInt(theScoreId);
			//getconnection to database
			myConn = dataSource.getConnection();
			//create sql to delete student
			String sql = "delete from diem where id=?;";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, scoreId);
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
		}
		
		
	}
	public List<StudentScore> searchStudents(String theSearchName)  throws Exception {
        List<StudentScore> students = new ArrayList<>();
        
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
                String sql = "select * from sinhvien where  lower(tensv) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
          
                
            } else {
                // create sql to get all students
                String sql = "select * from sinhvien order by tensv";
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
				int malop = myRs.getInt("malop");
				String sdt = myRs.getString("sdt");
                
                // create new student object
                StudentScore tempStudent = new StudentScore(masv, tensv, ngaysinh, gioitinh, diachi, malop, sdt);
                
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
