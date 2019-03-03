package cn.hu.system.domain;

import java.io.Serializable;

public class Recharge implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long frontuserid;

	private String rechargeamt;

	private String rechargeorderid;

	private String rechargestatus;

	private String rechargetime;

	private String rechargeupdatetime;

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

	public String getRechargeamt() {
		return rechargeamt;
	}

	public void setRechargeamt(String rechargeamt) {
		this.rechargeamt = rechargeamt;
	}

	public String getRechargeorderid() {
		return rechargeorderid;
	}

	public void setRechargeorderid(String rechargeorderid) {
		this.rechargeorderid = rechargeorderid;
	}

	public String getRechargestatus() {
		return rechargestatus;
	}

	public void setRechargestatus(String rechargestatus) {
		this.rechargestatus = rechargestatus;
	}

	public String getRechargetime() {
		return rechargetime;
	}

	public void setRechargetime(String rechargetime) {
		this.rechargetime = rechargetime;
	}

	public String getRechargeupdatetime() {
		return rechargeupdatetime;
	}

	public void setRechargeupdatetime(String rechargeupdatetime) {
		this.rechargeupdatetime = rechargeupdatetime;
	}

}
