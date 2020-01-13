package com.web.jdbc.classmanagement;

public class Class {
	private int malop;
	private String tenlop;
	private int makhoa;
	private String tenkhoa;
	private int siso;
	private boolean loailop;
	
	
	

	public Class(String tenlop, int makhoa, int siso, boolean loailop) {
		super();
		this.tenlop = tenlop;
		this.makhoa = makhoa;
		this.siso = siso;
		this.loailop = loailop;
	}

	public Class(int malop, String tenlop, int makhoa, String tenkhoa, int siso, boolean loailop) {
		super();
		this.malop = malop;
		this.tenlop = tenlop;
		this.makhoa = makhoa;
		this.tenkhoa = tenkhoa;
		this.siso = siso;
		this.loailop = loailop;
	}

	public Class(int malop, String tenlop, int makhoa, int siso, boolean loailop) {
		super();
		this.malop = malop;
		this.tenlop = tenlop;
		this.makhoa = makhoa;
		this.siso = siso;
		this.loailop = loailop;
	}

	public int getMalop() {
		return malop;
	}

	public void setMalop(int malop) {
		this.malop = malop;
	}

	public String getTenlop() {
		return tenlop;
	}

	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}

	public String getTenkhoa() {
		return tenkhoa;
	}

	public void setTenkhoa(String tenkhoa) {
		this.tenkhoa = tenkhoa;
	}

	public int getSiso() {
		return siso;
	}

	public void setSiso(int siso) {
		this.siso = siso;
	}

	public boolean isLoailop() {
		return loailop;
	}

	public void setLoailop(boolean loailop) {
		this.loailop = loailop;
	}


	public int getMakhoa() {
		return makhoa;
	}


	public void setMakhoa(int makhoa) {
		this.makhoa = makhoa;
	}

	@Override
	public String toString() {
		return "Class [malop=" + malop + ", tenlop=" + tenlop + ", makhoa=" + makhoa + ", tenkhoa=" + tenkhoa
				+ ", siso=" + siso + ", loailop=" + loailop + "]";
	}

	
	
	

}
