package cn.hu.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hu.common.domain.Tree;
import cn.hu.common.service.impl.BaseService;
import cn.hu.common.util.DateUtil;
import cn.hu.common.util.TreeUtils;
import cn.hu.system.dao.GoodsCategoryMapper;
import cn.hu.system.domain.GoodsCategory;
import cn.hu.system.service.GoodsCategoryService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("goodsCategoryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GoodsCategoryServiceImpl extends BaseService<GoodsCategory> implements GoodsCategoryService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	@Override
	public Tree<GoodsCategory> getGoodsCategoryTree() {
		List<Tree<GoodsCategory>> trees = new ArrayList<>();
		List<GoodsCategory> categorys = this.findAllGoodsCategorys(new GoodsCategory());
		categorys.forEach(category -> {
			Tree<GoodsCategory> tree = new Tree<>();
			tree.setId(category.getId().toString());
			tree.setParentId(category.getParentId().toString());
			tree.setText(category.getGoodscategory());
			trees.add(tree);
		});
		return TreeUtils.build(trees);
	}

	@Override
	public List<GoodsCategory> findAllGoodsCategorys(GoodsCategory category) {
		try {
			Example example = new Example(GoodsCategory.class);
			if (StringUtils.isNotBlank(category.getGoodscategory())) {
				example.createCriteria().andCondition("goodscategory=", category.getGoodscategory());
			}
			example.setOrderByClause("id");
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取商品分类列表失败", e);
			return new ArrayList<>();
		}

	}

	@Override
	public GoodsCategory findByName(String categoryName) {
		Example example = new Example(GoodsCategory.class);
		example.createCriteria().andCondition("lower(goodscategory) =", categoryName.toLowerCase());
		List<GoodsCategory> list = this.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@Transactional
	public void addGoodsCategory(GoodsCategory category) {
		Long parentId = category.getParentId();
		if (parentId == null) {
			category.setParentId(0L);
		}
		category.setCreatetime(DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:ss:mm"));
		this.save(category);
	}

	@Override
	@Transactional
	public void deleteGoodsCategorys(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
		this.batchDelete(list, "id", GoodsCategory.class);
		this.goodsCategoryMapper.changeToTop(list);
	}

	@Override
	public GoodsCategory selectById(Long id) {
		GoodsCategory gc = new GoodsCategory();
		gc.setId(id);
		return goodsCategoryMapper.selectById(gc);
	}

	@Override
	@Transactional
	public void updateGoodsCategory(GoodsCategory gc) {
		goodsCategoryMapper.updateGoodsCategory(gc);
	}

}
