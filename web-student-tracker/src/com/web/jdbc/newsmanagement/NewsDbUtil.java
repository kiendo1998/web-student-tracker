package com.web.jdbc.newsmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class NewsDbUtil {
	private DataSource dataSource;

	public NewsDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<News> searchNewses(String theSearchName) throws Exception{
    List<News> Newses = new ArrayList<>();
        
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
				  String sql = "select NewsID,NewsTitle,NewsContent,username" +
				  " from news " +
				  " where NewsTitle like ?";
				 
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                //myStmt.setString(2, theSearchNameLike);
                
            } else {
                // create sql to get all class
                String sql = "select NewsID,NewsTitle,NewsContent,username "
                		  +  "from news"
                		  + "order by NewsID";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int newsid = myRs.getInt("newsid");
                String newstitle = myRs.getString("newstitle");
                String newscontent = myRs.getString("newscontent");
                String username = myRs.getString("username");
                
                // create new class object
                News tempNews = new News(newsid, newstitle, newscontent, username);
                
                // add it to the list of classes
                Newses.add(tempNews);            
            }
            
            return Newses;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
	}
	public void deleteNews(String theNewsId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert Class id to int
			int newsId = Integer.parseInt(theNewsId);
			//getconnection to database
			myConn = dataSource.getConnection();
			//create sql to delete Class
			String sql = "delete  from news "
					+ " where newsid=?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, newsId);
			
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

	public void updateNews(News theNews) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		//create SQL update statement
		String sql=" update news "
				+ "set newstitle=?,newscontent=?,username=? "
				+ "where newsid=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		myStmt.setString(1, theNews.getNewstitle());
		myStmt.setString(2, theNews.getNewscontent());
		myStmt.setString(3, theNews.getUsername());
		myStmt.setInt(4,theNews.getNewsid());
		//execute SQL statement
		myStmt.execute();
			}
	finally {
		//clean up JDBC objects
		close(myConn, myStmt, null);
		}
	}
// for search feature
	public News getNews(String theNewsId) throws Exception {
News theNews = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int newsId;
		try {
			//convert subject id to int
			newsId = Integer.parseInt(theNewsId);
					
			//get connection to database
			myConn = dataSource.getConnection();
			//create sql to get selected class
			String sql = "select NewsID,NewsTitle,NewsContent,username"
					+ " from news"
					+ " where newsid=?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, newsId);
			//execute statement
			myRs = myStmt.executeQuery();
			//retrieve data from result set row
			if (myRs.next()) {
				int newsid = myRs.getInt("newsid");
				String title = myRs.getString("newstitle");
				String content = myRs.getString("newscontent");
				String username = myRs.getString("username");
				
				
				//use the classId during construction
				theNews= new News(newsid, title, content, username);
				
			}
			else {
				throw new Exception("Không tìm thấy mã tin : " + newsId);
			}
			
			
			
			return theNews;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
		
		
		
	}

	public void addNews(News theNews) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "insert into news"
			+ "(newstitle, newscontent,username) "
			+ "value (?, ?,?)"	;	
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			myStmt.setString(1, theNews.getNewstitle());
			myStmt.setString(2, theNews.getNewscontent());
			myStmt.setString(3, theNews.getUsername());
			//execute sql insert
			myStmt.execute();
		}
		finally {
		
		//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
		
	}

	public List<News> getNewses() throws Exception{
List<News> newses = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		//get a connection
		myConn = dataSource.getConnection();
		//create sql statement
		String sql = "select newsid,newstitle,newscontent,username"
				+ " from news";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while (myRs.next()) {
			//retrieve data from result set row
			int newsid = myRs.getInt("newsid");
			String newstitle = myRs.getString("newstitle");
			String newscontent = myRs.getString("newscontent");
			String username = myRs.getString("username");
			//create new class object
			News tempNews = new News(newsid, newstitle, newscontent, username);
			//add it to the list of class
			newses.add(tempNews);
		}
		
			
			
			return newses;
			
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	


	
	


}
