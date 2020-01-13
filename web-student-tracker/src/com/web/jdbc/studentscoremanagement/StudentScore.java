package com.web.jdbc.studentscoremanagement;

public class StudentScore {
	private int masv;
	private String tensv;
	private String ngaysinh;
	private String gioitinh;
	private String diachi;
	private int sotin;
	private float diemtichluy;
	//diem cua tung sinh vien
	private int mamh;
	private String tenmh;
	private float dqt;
	private float diemthi;
	private float diemkt;
	private String kyhoc;
	private int scoreid;
	
	
	public StudentScore(int masv, float dqt, float diemthi, int scoreid) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.scoreid = scoreid;
	}

	public StudentScore(float dqt, float diemthi) {
		super();
		this.dqt = dqt;
		this.diemthi = diemthi;
	}

	public StudentScore(int masv, float dqt, float diemthi, float diemkt, int scoreid) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
		this.scoreid = scoreid;
	}

	public StudentScore(int masv, int mamh, String tenmh, float dqt, float diemthi, float diemkt, String kyhoc,
			int scoreid) {
		super();
		this.masv = masv;
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
		this.kyhoc = kyhoc;
		this.scoreid = scoreid;
	}

	public StudentScore(int mamh, String tenmh, float dqt, float diemthi, float diemkt, String kyhoc, int scoreid) {
		super();
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
		this.kyhoc = kyhoc;
		this.scoreid = scoreid;
	}

	public StudentScore(int mamh, String tenmh, float dqt, float diemthi, float diemkt, String kyhoc) {
		super();
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
		this.kyhoc = kyhoc;
	}

	public int getScoreid() {
		return scoreid;
	}

	public void setScoreid(int scoreid) {
		this.scoreid = scoreid;
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

	public float getDqt() {
		return dqt;
	}

	public void setDqt(float dqt) {
		this.dqt = dqt;
	}

	public float getDiemthi() {
		return diemthi;
	}

	public void setDiemthi(float diemthi) {
		this.diemthi = diemthi;
	}

	public float getDiemkt() {
		return diemkt;
	}

	public void setDiemkt(float diemkt) {
		this.diemkt = diemkt;
	}

	public String getKyhoc() {
		return kyhoc;
	}

	public void setKyhoc(String kyhoc) {
		this.kyhoc = kyhoc;
	}

	public StudentScore(int masv, String tensv, String ngaysinh, String gioitinh, String diachi, int sotin,
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
	
	public StudentScore(String tensv, String ngaysinh, String gioitinh, String diachi, int sotin, float diemtichluy) {
		super();
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.sotin = sotin;
		this.diemtichluy = diemtichluy;
	}

	public StudentScore(int masv, String tensv, String ngaysinh, String gioitinh, String diachi) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
	}

	public StudentScore(String tensv, String ngaysinh, String gioitinh, String diachi) {
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
