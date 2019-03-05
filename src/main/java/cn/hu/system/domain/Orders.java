package cn.hu.system.domain;

import java.io.Serializable;

public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long backuserid;
	private String batchid;
	private String goodscategoryid;
	private Long goodscategoryname;
	private Long goodsid;
	private String goodsname;
	private String goodscount;
	private String goodsunitprice;
	private String goodstotalprice;
	private String createtime;
	private String orderstatus;
	private String orderstatusreason;
	private String frontreceiveaddressid;
	private String receiveprovince;
	private String receivecity;
	private String receivecountry;
	private String receivename;
	private String receivephone;
	private String postid;
	private String paystatus;
	private String poststatus;
	private String postcode;
	private String postcompany;
	private String postcontent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBackuserid() {
		return backuserid;
	}

	public void setBackuserid(Long backuserid) {
		this.backuserid = backuserid;
	}

	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public String getGoodscategoryid() {
		return goodscategoryid;
	}

	public void setGoodscategoryid(String goodscategoryid) {
		this.goodscategoryid = goodscategoryid;
	}

	public Long getGoodscategoryname() {
		return goodscategoryname;
	}

	public void setGoodscategoryname(Long goodscategoryname) {
		this.goodscategoryname = goodscategoryname;
	}

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodscount() {
		return goodscount;
	}

	public void setGoodscount(String goodscount) {
		this.goodscount = goodscount;
	}

	public String getGoodsunitprice() {
		return goodsunitprice;
	}

	public void setGoodsunitprice(String goodsunitprice) {
		this.goodsunitprice = goodsunitprice;
	}

	public String getGoodstotalprice() {
		return goodstotalprice;
	}

	public void setGoodstotalprice(String goodstotalprice) {
		this.goodstotalprice = goodstotalprice;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getOrderstatusreason() {
		return orderstatusreason;
	}

	public void setOrderstatusreason(String orderstatusreason) {
		this.orderstatusreason = orderstatusreason;
	}

	public String getFrontreceiveaddressid() {
		return frontreceiveaddressid;
	}

	public void setFrontreceiveaddressid(String frontreceiveaddressid) {
		this.frontreceiveaddressid = frontreceiveaddressid;
	}

	public String getReceiveprovince() {
		return receiveprovince;
	}

	public void setReceiveprovince(String receiveprovince) {
		this.receiveprovince = receiveprovince;
	}

	public String getReceivecity() {
		return receivecity;
	}

	public void setReceivecity(String receivecity) {
		this.receivecity = receivecity;
	}

	public String getReceivecountry() {
		return receivecountry;
	}

	public void setReceivecountry(String receivecountry) {
		this.receivecountry = receivecountry;
	}

	public String getReceivename() {
		return receivename;
	}

	public void setReceivename(String receivename) {
		this.receivename = receivename;
	}

	public String getReceivephone() {
		return receivephone;
	}

	public void setReceivephone(String receivephone) {
		this.receivephone = receivephone;
	}

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	public String getPoststatus() {
		return poststatus;
	}

	public void setPoststatus(String poststatus) {
		this.poststatus = poststatus;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostcompany() {
		return postcompany;
	}

	public void setPostcompany(String postcompany) {
		this.postcompany = postcompany;
	}

	public String getPostcontent() {
		return postcontent;
	}

	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
}
