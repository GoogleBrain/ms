package cn.hu.system.service;

import java.util.List;

import cn.hu.common.domain.QueryRequest;
import cn.hu.common.service.IService;
import cn.hu.system.domain.Rebate;

public interface RebateService extends IService<Rebate> {

	List<Rebate> findAllRebate(Rebate category,QueryRequest request);

	Rebate selectById(Long id);
}
