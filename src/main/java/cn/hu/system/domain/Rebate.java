package cn.hu.system.domain;

import java.io.Serializable;

/**
 * 返利
 * 
 * @author googlebrain
 *
 */
public class Rebate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer frontuserdetailsordersid;
	private String fromuserid;
	private String fromusername;
	private String fronuserphone;
	private Integer touserid;
	private String tousername;
	private String touserphone;
	private String rebateamt;
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFrontuserdetailsordersid() {
		return frontuserdetailsordersid;
	}

	public void setFrontuserdetailsordersid(Integer frontuserdetailsordersid) {
		this.frontuserdetailsordersid = frontuserdetailsordersid;
	}

	public String getFromuserid() {
		return fromuserid;
	}

	public void setFromuserid(String fromuserid) {
		this.fromuserid = fromuserid;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getFronuserphone() {
		return fronuserphone;
	}

	public void setFronuserphone(String fronuserphone) {
		this.fronuserphone = fronuserphone;
	}

	public Integer getTouserid() {
		return touserid;
	}

	public void setTouserid(Integer touserid) {
		this.touserid = touserid;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getTouserphone() {
		return touserphone;
	}

	public void setTouserphone(String touserphone) {
		this.touserphone = touserphone;
	}

	public String getRebateamt() {
		return rebateamt;
	}

	public void setRebateamt(String rebateamt) {
		this.rebateamt = rebateamt;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
