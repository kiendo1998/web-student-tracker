package com.web.jdbc.scoretablemanagement;

public class Score {
//	private int madiem;
//	private int mabd;
	private int masv;
	private String tensv;
	private int scoretableid;
	private float dqt;
	private float diemthi;
	private float diemkt;
	private int scoreid;
	
	
	
	
	public Score(int masv, float dqt, float diemthi, float diemkt, int scoreid) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
		this.scoreid = scoreid;
	}
	public Score(int scoretableid,int scoreid,int masv,  float dqt, float diemthi, float diemkt) {
		super();
		this.masv = masv;
		this.scoretableid = scoretableid;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
		this.scoreid = scoreid;
	}
	public Score(int masv, float dqt, float diemthi, float diemkt) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
	}
	public Score(int masv, int scoretableid, float dqt, float diemthi, int scoreid) {
		super();
		this.masv = masv;
		this.scoretableid = scoretableid;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.scoreid = scoreid;
	}
	public int getScoreid() {
		return scoreid;
	}
	public void setScoreid(int scoreid) {
		this.scoreid = scoreid;
	}
	public Score( int scoretableid,int masv, float dqt, float diemthi) {
		super();
		this.masv = masv;
		this.scoretableid = scoretableid;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt=(float)(this.diemthi*0.7+this.dqt*0.3);
	}
	public Score(int masv, float dqt, float diemthi) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;
	}
	public Score(int masv, String tensv, int scoretableid, float dqt, float diemthi, float diemkt) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.scoretableid = scoretableid;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.diemkt = diemkt;
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
	public int getScoretableid() {
		return scoretableid;
	}
	public void setScoretableid(int scoretableid) {
		this.scoretableid = scoretableid;
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
		return "Score [masv=" + masv + ", tensv=" + tensv + ", scoretableid=" + scoretableid + ", dqt=" + dqt
				+ ", diemthi=" + diemthi + ", diemkt=" + diemkt + "]";
	}



	

}
