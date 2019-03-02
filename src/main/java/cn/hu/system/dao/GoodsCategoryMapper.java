package cn.hu.system.dao;

import java.util.List;

import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.GoodsCategory;

public interface GoodsCategoryMapper extends MyMapper<GoodsCategory> {

	// 删除父节点，子节点变成顶级节点（根据实际业务调整）
	void changeToTop(List<String> id);

	public GoodsCategory selectById(GoodsCategory gc);
	
	void updateGoodsCategory(GoodsCategory gc);
}