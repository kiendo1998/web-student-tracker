package com.web.jdbc.studentmanagement;

public class Student {
	private int masv;
	private String tensv;
	private String ngaysinh;
	private String gioitinh;
	private String diachi;
	private int sotin;
	private float diemtichluy;
	
	
	public Student(int masv, String tensv, String ngaysinh, String gioitinh, String diachi, int sotin,
			float diemtichluy) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.sotin = sotin;
		this.diemtichluy = diemtichluy;
	}
	
	public Student(String tensv, String ngaysinh, String gioitinh, String diachi, int sotin, float diemtichluy) {
		super();
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.sotin = sotin;
		this.diemtichluy = diemtichluy;
	}

	public Student(int masv, String tensv, String ngaysinh, String gioitinh, String diachi) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
	}

	public Student(String tensv, String ngaysinh, String gioitinh, String diachi) {
		super();
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
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
	public int getSotin() {
		return sotin;
	}
	public void setSotin(int sotin) {
		this.sotin = sotin;
	}
	public float getDiemtichluy() {
		return diemtichluy;
	}
	public void setDiemtichluy(float diemtichluy) {
		this.diemtichluy = diemtichluy;
	}
	@Override
	public String toString() {
		return "Student [masv=" + masv + ", tensv=" + tensv + ", ngaysinh=" + ngaysinh + ", gioitinh=" + gioitinh
				+ ", diachi=" + diachi + ", sotin=" + sotin + ", diemtichluy=" + diemtichluy + "]";
	}
	
	
	
	
	
	

}
