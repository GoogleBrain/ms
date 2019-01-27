package cn.hu.job.dao;

import java.util.List;

import cn.hu.common.config.MyMapper;
import cn.hu.job.domain.Job;

public interface JobMapper extends MyMapper<Job> {
	
	List<Job> queryList();
}