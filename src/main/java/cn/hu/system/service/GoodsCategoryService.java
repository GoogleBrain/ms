package cn.hu.system.service;

import java.util.List;

import cn.hu.common.domain.Tree;
import cn.hu.common.service.IService;
import cn.hu.system.domain.Dept;
import cn.hu.system.domain.GoodsCategory;

public interface GoodsCategoryService extends IService<GoodsCategory> {

	Tree<GoodsCategory> getGoodsCategoryTree();

	List<GoodsCategory> findAllGoodsCategorys(GoodsCategory category);

	GoodsCategory findByName(String deptName);

	GoodsCategory selectById(Long id);

	void addGoodsCategory(GoodsCategory category);

	void updateGoodsCategory(GoodsCategory category);

	void deleteGoodsCategorys(String id);
}
