package cn.hu.system.service;

import java.util.List;

import cn.hu.common.service.IService;
import cn.hu.system.domain.ReceiveAddress;

public interface ReceiveAddressService extends IService<ReceiveAddress> {

	List<ReceiveAddress> getReceiveDetails(ReceiveAddress receiveAddress);
}
