package com.web.jdbc.scoretablemanagement;

public class ScoreTable {
	private int scoretableid;
	private int mamh;
	private int malop;
	private String kyhoc;
	private String tenmh;
	private String tenlop;
	public ScoreTable(int scoretableid, int mamh, int malop, String kyhoc, String tenmh, String tenlop) {
		super();
		this.scoretableid = scoretableid;
		this.mamh = mamh;
		this.malop = malop;
		this.kyhoc = kyhoc;
		this.tenmh = tenmh;
		this.tenlop = tenlop;
	}
	public ScoreTable(int mamh, int malop, String kyhoc) {
		super();
		this.mamh = mamh;
		this.malop = malop;
		this.kyhoc = kyhoc;
	}
	public ScoreTable(int scoretableid, int mamh, int malop, String kyhoc) {
		super();
		this.scoretableid = scoretableid;
		this.mamh = mamh;
		this.malop = malop;
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
	public int getMalop() {
		return malop;
	}
	public void setMalop(int malop) {
		this.malop = malop;
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
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}
	@Override
	public String toString() {
		return "ScoreTable [scoretableid=" + scoretableid + ", mamh=" + mamh + ", malop=" + malop + ", kyhoc=" + kyhoc
				+ ", tenmh=" + tenmh + ", tenlop=" + tenlop + "]";
	}
	
	
	
	
	

}
