package com.web.jdbc.scoremanagement;

public class Score {
//	private int madiem;
//	private int mabd;
	private int masv;
	private String tensv;
	private String tenlop;
	private String tenmh;
	private float dqt;
	private float diemthi;
	private float diemkt;
	private String kyhoc;


	

	public Score(int masv, String tenlop, String tenmh, float dqt, float diemthi, String kyhoc) {
		super();
		this.masv = masv;
		this.tenlop = tenlop;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.kyhoc = kyhoc;
	}

	public Score(int masv, String tensv, String tenlop, String tenmh, float dqt, float diemthi, float diemkt,
			String kyhoc) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.tenlop = tenlop;
		this.tenmh = tenmh;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
		this.kyhoc = kyhoc;
	}

	public String getKyhoc() {
		return kyhoc;
	}

	public void setKyhoc(String kyhoc) {
		this.kyhoc = kyhoc;
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
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
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




	@Override
	public String toString() {
		return "Score [masv=" + masv + ", tensv=" + tensv + ", tenlop=" + tenlop + ", tenmh=" + tenmh + ", dqt=" + dqt
				+ ", diemthi=" + diemthi + ", diemkt=" + diemkt + "]";
	}
	
	

}
