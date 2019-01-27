package cn.hu.system.service;

import cn.hu.common.service.IService;
import cn.hu.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
