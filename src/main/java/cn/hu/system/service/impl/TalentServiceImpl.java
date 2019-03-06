package cn.hu.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hu.common.domain.QueryRequest;
import cn.hu.common.service.impl.BaseService;
import cn.hu.common.util.MD5Utils;
import cn.hu.system.dao.TalentMapper;
import cn.hu.system.dao.UserMapper;
import cn.hu.system.dao.UserRoleMapper;
import cn.hu.system.domain.GoodsCategory;
import cn.hu.system.domain.Talent;
import cn.hu.system.domain.User;
import cn.hu.system.domain.UserRole;
import cn.hu.system.domain.UserWithRole;
import cn.hu.system.service.TalentService;
import cn.hu.system.service.UserRoleService;
import cn.hu.system.service.UserService;
import tk.mybatis.mapper.entity.Example;

@Service("talentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TalentServiceImpl extends BaseService<Talent> implements TalentService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TalentMapper talentMapper;

	@Override
	public List<Talent> findAllTalents(Talent category) {
		try {
			Example example = new Example(Talent.class);
			// if (StringUtils.isNotBlank(category.getGoodscategory())) {
			// example.createCriteria().andCondition("goodscategory=",
			// category.getGoodscategory());
			// }
			example.setOrderByClause("id");
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取商品分类列表失败", e);
			return new ArrayList<>();
		}

	}

	@Override
	public Talent findByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTalent(Talent talent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTalent(Talent talent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTalent(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Talent selectById(Long id) {
		Talent tt = new Talent();
		tt.setId(id);
		return talentMapper.selectById(tt);
	}

}
