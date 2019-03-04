package cn.hu.system.dao;

import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.Dict;
import cn.hu.system.domain.GoodsCategory;
import cn.hu.system.domain.WithDraw;

public interface WithDrawMapper extends MyMapper<WithDraw> {

	public WithDraw selectById(WithDraw gc);
}