package cn.hu.system.service;

import java.util.List;

import cn.hu.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
