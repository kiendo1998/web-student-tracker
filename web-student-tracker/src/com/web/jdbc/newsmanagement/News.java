package com.web.jdbc.newsmanagement;

public class News {
	private int newsid;
	private String newstitle;
	private String newscontent;
	private String username;
	
	
	
	public News(String newstitle, String newscontent, String username) {
		this.newstitle = newstitle;
		this.newscontent = newscontent;
		this.username = username;
	}
	public int getNewsid() {
		return newsid;
	}
	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	public String getNewscontent() {
		return newscontent;
	}
	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public News(int newsid, String newstitle, String newscontent, String username) {
		this.newsid = newsid;
		this.newstitle = newstitle;
		this.newscontent = newscontent;
		this.username = username;
	}
	@Override
	public String toString() {
		return "News [newsid=" + newsid + ", newstitle=" + newstitle + ", newscontent=" + newscontent + ", username="
				+ username + "]";
	}
	
	
	
	

}
