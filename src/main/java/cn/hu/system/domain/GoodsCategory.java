package cn.hu.system.domain;

import java.io.Serializable;

public class GoodsCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String goodscategory;

	private Long parentId;

	private String createtime;

	public String getGoodscategory() {
		return goodscategory;
	}

	public void setGoodscategory(String goodscategory) {
		this.goodscategory = goodscategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
