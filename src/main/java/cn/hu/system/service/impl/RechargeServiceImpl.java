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
import cn.hu.system.dao.RechargeMapper;
import cn.hu.system.domain.GoodsCategory;
import cn.hu.system.domain.Recharge;
import cn.hu.system.service.GoodsCategoryService;
import cn.hu.system.service.RechargeService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("rechargeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RechargeServiceImpl extends BaseService<Recharge> implements RechargeService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RechargeMapper rechargeMapper;

	@Override
	public List<Recharge> findAllRecharge(Recharge category) {
		try {
			Example example = new Example(Recharge.class);
			if (category.getId()!=null) {
				example.createCriteria().andCondition("id=", category.getId());
			}
			example.setOrderByClause("id");
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取商品分类列表失败", e);
			return new ArrayList<>();
		}

	}

//	@Override
//	public Recharge findByName(String categoryName) {
//		Example example = new Example(GoodsCategory.class);
//		example.createCriteria().andCondition("lower(goodscategory) =", categoryName.toLowerCase());
//		List<GoodsCategory> list = this.selectByExample(example);
//		return list.isEmpty() ? null : list.get(0);
//	}

//	@Override
//	@Transactional
//	public void addGoodsCategory(GoodsCategory category) {
//		Long parentId = category.getParentId();
//		if (parentId == null) {
//			category.setParentId(0L);
//		}
//		category.setCreatetime(DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:ss:mm"));
//		this.save(category);
//	}

//	@Override
//	@Transactional
//	public void deleteGoodsCategorys(String ids) {
//		List<String> list = Arrays.asList(ids.split(","));
//		this.batchDelete(list, "id", GoodsCategory.class);
//		this.goodsCategoryMapper.changeToTop(list);
//	}

//	@Override
//	public GoodsCategory selectById(Long id) {
//		GoodsCategory gc = new GoodsCategory();
//		gc.setId(id);
//		return goodsCategoryMapper.selectById(gc);
//	}

//	@Override
//	@Transactional
//	public void updateGoodsCategory(GoodsCategory gc) {
//		goodsCategoryMapper.updateGoodsCategory(gc);
//	}

}
