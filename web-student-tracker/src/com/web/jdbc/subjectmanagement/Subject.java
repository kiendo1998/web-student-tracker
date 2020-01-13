package com.web.jdbc.subjectmanagement;

public class Subject {
	private int mamh;
	private String tenmh;
	private int sotc;
	
	
	
	
	public Subject(String tenmh, int sotc) {
		this.tenmh = tenmh;
		this.sotc = sotc;
	}
	public Subject(int mamh, String tenmh, int sotc) {
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.sotc = sotc;
	}
	public int getMamh() {
		return mamh;
	}
	public void setMamh(int mamh) {
		this.mamh = mamh;
	}
	public String getTenmh() {
		return tenmh;
	}
	public void setTenmh(String tenmh) {
		this.tenmh = tenmh;
	}
	public int getSotc() {
		return sotc;
	}
	public void setSotc(int sotc) {
		this.sotc = sotc;
	}
	
	
	
	

}
