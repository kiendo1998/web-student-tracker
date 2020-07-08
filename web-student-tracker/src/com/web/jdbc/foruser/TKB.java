package com.web.jdbc.foruser;

public class TKB {

	private String monhoc;
	private String tkb;
	public String getMonhoc() {
		return monhoc;
	}
	public void setMonhoc(String monhoc) {
		this.monhoc = monhoc;
	}
	public String getTkb() {
		return tkb;
	}
	public void setTkb(String tkb) {
		this.tkb = tkb;
	}
	public TKB(String monhoc, String tkb) {
		this.monhoc = monhoc;
		this.tkb = tkb;
	}
	@Override
	public String toString() {
		return "TKB [monhoc=" + monhoc + ", tkb=" + tkb + "]";
	}
	
	
	


	
	
	
	
	

}
