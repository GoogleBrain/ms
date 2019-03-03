package cn.hu.system.service;

import java.util.List;

import cn.hu.common.service.IService;
import cn.hu.system.domain.Recharge;

public interface RechargeService extends IService<Recharge> {

	List<Recharge> findAllRecharge(Recharge category);

//	Recharge findByName(String deptName);
//
//	Recharge selectById(Long id);
//
//	void addRecharge(Recharge category);
//
//	void updateRecharge(Recharge category);
//
//	void deleteRecharge(String id);
}
