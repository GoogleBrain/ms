package cn.hu.system.domain;

import java.io.Serializable;

public class Talent implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long talentid;
	private String amt;// 账户总余额
	private String talentrealname;
	private String talentaddress;
	private String talentphone;
	private String cardfront;
	private String cardback;
	private String license;
	private String weixin;
	private String zhifubao;
	private String bankcard;
	private String examstatus;
	private String examreason;
	private String createtime;
	private String ifrealname;
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTalentid() {
		return talentid;
	}

	public void setTalentid(Long talentid) {
		this.talentid = talentid;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getTalentrealname() {
		return talentrealname;
	}

	public void setTalentrealname(String talentrealname) {
		this.talentrealname = talentrealname;
	}

	public String getTalentaddress() {
		return talentaddress;
	}

	public void setTalentaddress(String talentaddress) {
		this.talentaddress = talentaddress;
	}

	public String getTalentphone() {
		return talentphone;
	}

	public void setTalentphone(String talentphone) {
		this.talentphone = talentphone;
	}

	public String getCardfront() {
		return cardfront;
	}

	public void setCardfront(String cardfront) {
		this.cardfront = cardfront;
	}

	public String getCardback() {
		return cardback;
	}

	public void setCardback(String cardback) {
		this.cardback = cardback;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getZhifubao() {
		return zhifubao;
	}

	public void setZhifubao(String zhifubao) {
		this.zhifubao = zhifubao;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getExamstatus() {
		return examstatus;
	}

	public void setExamstatus(String examstatus) {
		this.examstatus = examstatus;
	}

	public String getExamreason() {
		return examreason;
	}

	public void setExamreason(String examreason) {
		this.examreason = examreason;
	}

	public String getIfrealname() {
		return ifrealname;
	}

	public void setIfrealname(String ifrealname) {
		this.ifrealname = ifrealname;
	}
}
