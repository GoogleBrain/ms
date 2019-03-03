package cn.hu.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hu.common.domain.QueryRequest;
import cn.hu.common.service.impl.BaseService;
import cn.hu.system.domain.Dict;
import cn.hu.system.domain.Rebate;
import cn.hu.system.service.DictService;
import cn.hu.system.service.RebateService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("rebateService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RebateServiceImpl extends BaseService<Rebate> implements RebateService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Rebate> findAllRebate(Rebate dict, QueryRequest request) {
		try {
			Example example = new Example(Dict.class);
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
	public Rebate selectById(Long id) {
		return this.selectByKey(id);
	}
}
