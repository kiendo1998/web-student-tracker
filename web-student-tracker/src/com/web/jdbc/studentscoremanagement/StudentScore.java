package com.web.jdbc.studentscoremanagement;

public class StudentScore {
	private int masv;
	private String tensv;
	private String ngaysinh;
	private String gioitinh;
	private String diachi;
	private int malop;
	private String sdt;
	//diem cua tung sinh vien
	private int mamh;
	private String tenmh;
	private float dqt;
	private float diemthi;
	private String kyhoc;
	private int id;
	private float diemhe4;
	private String diemchu;
	
	public StudentScore(float dqt, float diemthi) {
		super();
		this.dqt = dqt;
		this.diemthi = diemthi;
	}

	public StudentScore(int masv, float dqt, float diemthi, int id) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;

		this.id = id;
	}

	public StudentScore(int masv, int mamh, String tenmh, float dqt, float diemthi, String kyhoc,
			int id, float diemhe4, String diemchu) {
		super();
		this.masv = masv;
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;

		this.kyhoc = kyhoc;
		this.id = id;
		this.diemhe4 = diemhe4;
		this.diemchu = diemchu;
		
	}

	public StudentScore(int mamh, String tenmh, float dqt, float diemthi, String kyhoc, int id) {
		super();
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.kyhoc = kyhoc;
		this.id = id;
	}

	public StudentScore(int mamh, String tenmh, float dqt, float diemthi, String kyhoc) {
		super();
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.kyhoc = kyhoc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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



	public String getKyhoc() {
		return kyhoc;
	}

	public void setKyhoc(String kyhoc) {
		this.kyhoc = kyhoc;
	}

	public StudentScore(int masv, String tensv, String ngaysinh, String gioitinh, String diachi, int malop,
			String sdt) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.malop = malop;
		this.sdt = sdt;
	}
	
	public StudentScore(String tensv, String ngaysinh, String gioitinh, String diachi, int malop,
			String sdt) {
		super();
		this.tensv = tensv;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.diachi = diachi;
		this.malop = malop;
		this.sdt = sdt;
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

	public float getDiemhe4() {
		return diemhe4;
	}

	public void setDiemhe4(float diemhe4) {
		this.diemhe4 = diemhe4;
	}

	public String getDiemchu() {
		return diemchu;
	}

	public void setDiemchu(String diemchu) {
		this.diemchu = diemchu;
	}

	@Override
	public String toString() {
		return "StudentScore [masv=" + masv + ", tensv=" + tensv + ", ngaysinh=" + ngaysinh + ", gioitinh=" + gioitinh
				+ ", diachi=" + diachi + ", malop=" + malop + ", sdt=" + sdt + ", mamh=" + mamh + ", tenmh=" + tenmh
				+ ", dqt=" + dqt + ", diemthi=" + diemthi + ", kyhoc=" + kyhoc + ", id=" + id + ", diemhe4=" + diemhe4
				+ ", diemchu=" + diemchu + "]";
	}


	
	
	
	
	

}
