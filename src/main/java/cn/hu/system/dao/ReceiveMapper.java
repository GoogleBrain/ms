package cn.hu.system.dao;

import java.util.List;
import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.ReceiveAddress;

public interface ReceiveMapper extends MyMapper<ReceiveAddress> {
	List<ReceiveAddress> getReceiveDetails(ReceiveAddress receiveAddress);
}