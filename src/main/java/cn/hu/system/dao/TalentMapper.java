package cn.hu.system.dao;

import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.Talent;

public interface TalentMapper extends MyMapper<Talent> {
	public Talent selectById(Talent gc);
}