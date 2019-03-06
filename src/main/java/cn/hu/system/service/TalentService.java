package cn.hu.system.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import cn.hu.common.service.IService;
import cn.hu.system.domain.Talent;

@CacheConfig(cacheNames = "talentService")
public interface TalentService extends IService<Talent> {

	public List<Talent> findAllTalents(Talent category);

	Talent findByName(String userName);

	void addTalent(Talent talent);

	void updateTalent(Talent talent);

	void deleteTalent(String id);
	
	public Talent selectById(Long gc);

}
