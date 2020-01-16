package com.web.jdbc.studentmanagement;

public class Student {
	private int masv;
	private String tensv;
	private String ngaysinh;
	private String gioitinh;
	private String diachi;
	private int malop;
	private String sdt;
	
	
	

	public Student(int masv, String tensv, String ngaysinh, String gioitinh, String diachi,int malop, String sdt) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.malop = malop;
		this.sdt = sdt;
	}

	public Student(String tensv, String ngaysinh, String gioitinh, String diachi,int malop, String sdt) {
		super();
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.malop = malop;
		this.sdt = sdt;
	}
	
	public int getMalop() {
		return malop;
	}

	public void setMalop(int malop) {
		this.malop = malop;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getMasv() {
		return masv;
	}
	public void setMasv(int masv) {
		this.masv = masv;
	}
	public String getTensv() {
		return tensv;
	}
	public void setTensv(String tensv) {
		this.tensv = tensv;
	}
	public String getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	@Override
	public String toString() {
		return "Student [masv=" + masv + ", tensv=" + tensv + ", ngaysinh=" + ngaysinh + ", gioitinh=" + gioitinh
				+ ", diachi=" + diachi +  "]";
	}
	
	
	
	
	
	

}
