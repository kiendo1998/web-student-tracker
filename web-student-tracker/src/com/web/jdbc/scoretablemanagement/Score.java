package com.web.jdbc.scoretablemanagement;

public class Score {
//	private int madiem;
//	private int mabd;
	private int masv;
	private String tensv;
	private int scoretableid;
	private float dqt;
	private float diemthi;
	private int scoreid;
	private String username;
	
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Score(int masv, int scoretableid) {
		this.masv = masv;
		this.scoretableid = scoretableid;
	}
	public Score(int scoretableid, String username) {
		this.scoretableid = scoretableid;
		this.username = username;
	}
	public Score(int masv, float dqt, float diemthi, int scoreid) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.scoreid = scoreid;
	}
	public Score(int scoretableid,int scoreid,int masv,  float dqt, float diemthi) {
		super();
		this.masv = masv;
		this.scoretableid = scoretableid;
		this.dqt = dqt;
		this.diemthi = diemthi;
		this.scoreid = scoreid;
	}
	public Score(int masv, float dqt, float diemthi) {
		super();
		this.masv = masv;
		this.dqt = dqt;
		this.diemthi = diemthi;
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
	}
	public Score(int masv, String tensv, int scoretableid, float dqt, float diemthi) {
		super();
		this.masv = masv;
		this.tensv = tensv;
		this.scoretableid = scoretableid;
		this.dqt = dqt;
		this.diemthi = diemthi;
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
	@Override
	public String toString() {
		return "Score [masv=" + masv + ", tensv=" + tensv + ", scoretableid=" + scoretableid + ", dqt=" + dqt
				+ ", diemthi=" + diemthi + "]";
	}



	

}
