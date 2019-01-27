package cn.hu.system.dao;

import java.util.List;

import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.User;
import cn.hu.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}