package cn.hu.system.dao;

import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.Rebate;
public interface RebateMapper extends MyMapper<Rebate> {

	public Rebate selectById(Rebate gc);
}