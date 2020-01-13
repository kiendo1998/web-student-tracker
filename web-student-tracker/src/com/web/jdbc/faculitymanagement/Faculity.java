package com.web.jdbc.faculitymanagement;

public class Faculity {
	private int makhoa;
	private String tenkhoa;
	public int getMakhoa() {
		return makhoa;
	}
	public void setMakhoa(int makhoa) {
		this.makhoa = makhoa;
	}
	public String getTenkhoa() {
		return tenkhoa;
	}
	public void setTenkhoa(String tenkhoa) {
		this.tenkhoa = tenkhoa;
	}
	public Faculity(int makhoa, String tenkhoa) {
		super();
		this.makhoa = makhoa;
		this.tenkhoa = tenkhoa;
	}
	public Faculity(String tenkhoa) {
		super();
		this.tenkhoa = tenkhoa;
	}
	@Override
	public String toString() {
		return "Faculity [makhoa=" + makhoa + ", tenkhoa=" + tenkhoa + "]";
	}

	
	
	
	
	
	
	
	

}
