package cn.hu.system.dao;

import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.Orders;
public interface OrdersMapper extends MyMapper<Orders> {

	public Orders selectById(Orders gc);
}