package cn.hu.system.service;

import cn.hu.common.domain.QueryRequest;
import cn.hu.common.service.IService;
import cn.hu.system.domain.WithDraw;

import java.util.List;

public interface WithDrawService extends IService<WithDraw> {

	List<WithDraw> findAllWithDraw(WithDraw withDraw, QueryRequest request);

	public WithDraw selectById(WithDraw gc);

	void addWithDraw(WithDraw withDraw);

	void deleteWithDraw(String dictIds);

	void updateWithDraw(WithDraw withDraw);
}
