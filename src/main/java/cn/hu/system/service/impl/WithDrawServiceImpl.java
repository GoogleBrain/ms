package cn.hu.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hu.common.domain.QueryRequest;
import cn.hu.common.service.impl.BaseService;
import cn.hu.system.dao.WithDrawMapper;
import cn.hu.system.domain.WithDraw;
import cn.hu.system.service.WithDrawService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("withDrawService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WithDrawServiceImpl extends BaseService<WithDraw> implements WithDrawService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WithDrawMapper mapper;

	@Override
	public List<WithDraw> findAllWithDraw(WithDraw withDraw, QueryRequest request) {
		try {
			Example example = new Example(WithDraw.class);
			Criteria criteria = example.createCriteria();
			// if (StringUtils.isNotBlank(dict.getKeyy())) {
			// criteria.andCondition("keyy=", Long.valueOf(dict.getKeyy()));
			// }
			// if (StringUtils.isNotBlank(dict.getValuee())) {
			// criteria.andCondition("valuee=", dict.getValuee());
			// }
			// if (StringUtils.isNotBlank(dict.getTableName())) {
			// criteria.andCondition("table_name=", dict.getTableName());
			// }
			// if (StringUtils.isNotBlank(dict.getFieldName())) {
			// criteria.andCondition("field_name=", dict.getFieldName());
			// }
			example.setOrderByClause("id");
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取字典信息失败", e);
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional
	public void addWithDraw(WithDraw withDraw) {
		this.save(withDraw);
	}

	@Override
	@Transactional
	public void deleteWithDraw(String dictIds) {
		List<String> list = Arrays.asList(dictIds.split(","));
		this.batchDelete(list, "id", WithDraw.class);
	}

	@Override
	@Transactional
	public void updateWithDraw(WithDraw withDraw) {
		this.updateNotNull(withDraw);
	}

	@Override
	public WithDraw selectById(WithDraw gc) {
		return mapper.selectById(gc);
	}

}
