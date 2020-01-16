package com.web.jdbc.faculitymanagement;

public class Faculity {
	private int makhoa;
	private String tenkhoa;
	private String sdt;
	
	public Faculity(int makhoa, String tenkhoa, String sdt) {
		this.makhoa = makhoa;
		this.tenkhoa = tenkhoa;
		this.sdt = sdt;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
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
	public Faculity(String tenkhoa, String sdt) {
		super();
		this.tenkhoa = tenkhoa;
		this.sdt = sdt;
	}
	@Override
	public String toString() {
		return "Faculity [makhoa=" + makhoa + ", tenkhoa=" + tenkhoa + "]";
	}

	
	
	
	
	
	
	
	

}
