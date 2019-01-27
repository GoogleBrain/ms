package cn.hu.system.dao;

import java.util.List;

import cn.hu.common.config.MyMapper;
import cn.hu.system.domain.Role;
import cn.hu.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}