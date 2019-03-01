package cn.hu.system.domain;

import java.io.Serializable;

public class ReceiveAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nickname;
	private String receiveprovince;
	private String receivecity;
	private String receivecounty;
	private String receivedetailaddress;
	private String receivephone;
	private String receivenickname;
	private String createtime;

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getReceivecounty() {
		return receivecounty;
	}

	public void setReceivecounty(String receivecounty) {
		this.receivecounty = receivecounty;
	}

	public String getReceivedetailaddress() {
		return receivedetailaddress;
	}

	public void setReceivedetailaddress(String receivedetailaddress) {
		this.receivedetailaddress = receivedetailaddress;
	}

	public String getReceivephone() {
		return receivephone;
	}

	public void setReceivephone(String receivephone) {
		this.receivephone = receivephone;
	}

	public String getReceivenickname() {
		return receivenickname;
	}

	public void setReceivenickname(String receivenickname) {
		this.receivenickname = receivenickname;
	}
}
