package com.web.jdbc.newsmanagement;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class ClassControllerServlet
 */
@WebServlet("/NewsControllerServlet")
public class NewsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NewsDbUtil newsDbUtil;
	
	@Resource(name="jdbc/quan_ly_sinh_vien")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our student db util ... and pass in the conn pool / datasource
		try {
			newsDbUtil = new NewsDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}




	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
				
			}
			
			//route to the appropriate mathod
			switch (theCommand) {
			
			case "LIST1":
				listNewses(request, response);
				break;
			case "LIST":
				listNewses1(request, response);
				break;
				
			case "ADD":
				addNews(request,response);
				break;
			case "LOAD":
				loadNews(request,response);
				break;
			case "UPDATE":
				updateNews(request, response);
			    break;
			case "DELETE":
				deleteNews(request, response);
				break;
			case "SEARCH":
                searchNewses(request, response);
                break;
			
			}
		// list the class ... in MVC fashion
		listNewses(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}



	private void searchNewses(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<News> newses = newsDbUtil.searchNewses(theSearchName);
        
        // add students to the request
        request.setAttribute("NEWS_LIST", newses);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("NewsManage/list-news.jsp");
        dispatcher.forward(request, response);
    }

	
	
	private void deleteNews(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
	
		// read class id from form data
		String theNewsId = request.getParameter("newsId");
		
		//delete student from database
		newsDbUtil.deleteNews(theNewsId);
		//send them back to "list classes" page
		listNewses(request, response);
		
		
		
	}





	private void updateNews(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		//read class infor from form data
		int newsid=Integer.parseInt(request.getParameter("newsId"));
		String newstitle = request.getParameter("newstitle");
		String newscontent=request.getParameter("newscontent");
		String username = request.getParameter("username");
		//create a new student object
		News theNews = new News(newsid, newstitle, newscontent, username);
		
		//perform update on database
		newsDbUtil.updateNews(theNews);
		
		//send them abck to the "list classes" page
		listNewses(request, response);
	}





	private void loadNews(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		
		//read class id from form data
		String theNewsId = request.getParameter("newsId");
		//get class from database (db util)
		News theNews = newsDbUtil.getNews(theNewsId);
		//place class in the request attribute
		request.setAttribute("THE_NEWS", theNews);
		//send to jsp page: update-class-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("NewsManage/update-news-form.jsp");
		dispatcher.forward(request, response);
	}





	private void addNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// read class infor from form data
		//int masv=Integer.parseInt(request.getParameter("masv"));
		String newstitle = request.getParameter("tenlop");
		String newscontent = request.getParameter("newscontent");
		String username = request.getParameter("username");
		//create a new class object
		News theNews = new News(newstitle, newscontent, username);
		
		// add the student to the database
		newsDbUtil.addNews(theNews);
		// send back to main page (the class list)
		listNewses(request, response);
	}





	private void listNewses(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//get class from db util
		List<News> newses = newsDbUtil.getNewses();
		//add classes to the request
		request.setAttribute("NEWS_LIST", newses);
		//send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/NewsManage/list-news.jsp");
		dispatcher.forward(request, response);
	}
	private void listNewses1(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
				//get class from db util
				List<News> newses = newsDbUtil.getNewses();
				//add classes to the request
				request.setAttribute("NEWS_LIST", newses);
				//send to jsp page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/NewsManage/show-news.jsp");
				dispatcher.forward(request, response);
			}
	
	

}
