package com.web.jdbc.scoretablemanagement;

public class ScoreTable {
	private int scoretableid;
	private int mamh;
	private String kyhoc;
	private String tenmh;
	private int magv;
	private String lichhoc;
	
	
	public ScoreTable(int mamh, String kyhoc, int magv, String lichhoc) {
		this.mamh = mamh;
		this.kyhoc = kyhoc;
		this.magv = magv;
		this.lichhoc = lichhoc;
	}
	public String getLichhoc() {
		return lichhoc;
	}
	public void setLichhoc(String lichhoc) {
		this.lichhoc = lichhoc;
	}
	public int getMagv() {
		return magv;
	}
	public void setMagv(int magv) {
		this.magv = magv;
	}
	public ScoreTable(int mamh, String kyhoc, int magv) {
		this.mamh = mamh;
		this.kyhoc = kyhoc;
		this.magv = magv;
	}
	public ScoreTable(int scoretableid, int mamh, String kyhoc, String tenmh) {
		super();
		this.scoretableid = scoretableid;
		this.mamh = mamh;
		this.kyhoc = kyhoc;
		this.tenmh = tenmh;
	}
	public ScoreTable(int mamh, String kyhoc) {
		super();
		this.mamh = mamh;
		this.kyhoc = kyhoc;
	}
	public ScoreTable(int scoretableid, int mamh, String kyhoc) {
		super();
		this.scoretableid = scoretableid;
		this.mamh = mamh;
		this.kyhoc = kyhoc;
	}
	public int getScoretableid() {
		return scoretableid;
	}
	public void setScoretableid(int scoretableid) {
		this.scoretableid = scoretableid;
	}
	public int getMamh() {
		return mamh;
	}
	public void setMamh(int mamh) {
		this.mamh = mamh;
	}
	public String getKyhoc() {
		return kyhoc;
	}
	public void setKyhoc(String kyhoc) {
		this.kyhoc = kyhoc;
	}
	public String getTenmh() {
		return tenmh;
	}
	public void setTenmh(String tenmh) {
		this.tenmh = tenmh;
	}
	@Override
	public String toString() {
		return "ScoreTable [scoretableid=" + scoretableid + ", mamh=" + mamh +  ", kyhoc=" + kyhoc
				+ ", tenmh=" + tenmh + "]";
	}
	
	
	
	
	

}
