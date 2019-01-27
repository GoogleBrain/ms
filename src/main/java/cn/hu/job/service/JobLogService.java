package cn.hu.job.service;

import java.util.List;

import cn.hu.common.service.IService;
import cn.hu.job.domain.JobLog;

public interface JobLogService extends IService<JobLog>{

	List<JobLog> findAllJobLogs(JobLog jobLog);

	void saveJobLog(JobLog log);
	
	void deleteBatch(String jobLogIds);
}
