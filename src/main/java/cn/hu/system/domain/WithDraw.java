package cn.hu.system.domain;

import java.io.Serializable;

public class WithDraw implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long frontuserid;
	private Long backuserid;
	private String withdrawamt;
	private String withdrawmethod;
	private String withdrawtime;
	private String withdrawupdatetime;
	private String withdrawexamstatus;
	private String withdrawexamreason;
	private String withdrawstatus;
	private String withdraworderid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFrontuserid() {
		return frontuserid;
	}

	public void setFrontuserid(Long frontuserid) {
		this.frontuserid = frontuserid;
	}

	public Long getBackuserid() {
		return backuserid;
	}

	public void setBackuserid(Long backuserid) {
		this.backuserid = backuserid;
	}

	public String getWithdrawamt() {
		return withdrawamt;
	}

	public void setWithdrawamt(String withdrawamt) {
		this.withdrawamt = withdrawamt;
	}

	public String getWithdrawmethod() {
		return withdrawmethod;
	}

	public void setWithdrawmethod(String withdrawmethod) {
		this.withdrawmethod = withdrawmethod;
	}

	public String getWithdrawtime() {
		return withdrawtime;
	}

	public void setWithdrawtime(String withdrawtime) {
		this.withdrawtime = withdrawtime;
	}

	public String getWithdrawupdatetime() {
		return withdrawupdatetime;
	}

	public void setWithdrawupdatetime(String withdrawupdatetime) {
		this.withdrawupdatetime = withdrawupdatetime;
	}

	public String getWithdrawexamstatus() {
		return withdrawexamstatus;
	}

	public void setWithdrawexamstatus(String withdrawexamstatus) {
		this.withdrawexamstatus = withdrawexamstatus;
	}

	public String getWithdrawexamreason() {
		return withdrawexamreason;
	}

	public void setWithdrawexamreason(String withdrawexamreason) {
		this.withdrawexamreason = withdrawexamreason;
	}

	public String getWithdrawstatus() {
		return withdrawstatus;
	}

	public void setWithdrawstatus(String withdrawstatus) {
		this.withdrawstatus = withdrawstatus;
	}

	public String getWithdraworderid() {
		return withdraworderid;
	}

	public void setWithdraworderid(String withdraworderid) {
		this.withdraworderid = withdraworderid;
	}
}
