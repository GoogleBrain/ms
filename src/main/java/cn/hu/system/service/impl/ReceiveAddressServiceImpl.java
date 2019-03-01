package cn.hu.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hu.common.service.impl.BaseService;
import cn.hu.system.dao.ReceiveMapper;
import cn.hu.system.dao.RoleMapper;
import cn.hu.system.dao.RoleMenuMapper;
import cn.hu.system.domain.ReceiveAddress;
import cn.hu.system.domain.Role;
import cn.hu.system.domain.RoleMenu;
import cn.hu.system.domain.RoleWithMenu;
import cn.hu.system.service.ReceiveAddressService;
import cn.hu.system.service.RoleMenuServie;
import cn.hu.system.service.RoleService;
import cn.hu.system.service.UserRoleService;
import tk.mybatis.mapper.entity.Example;

@Service("receiveAddressService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ReceiveAddressServiceImpl extends BaseService<ReceiveAddress> implements ReceiveAddressService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReceiveMapper receiveMapper;

	@Override
	public List<ReceiveAddress> getReceiveDetails(ReceiveAddress receiveAddress) {
		return receiveMapper.getReceiveDetails(receiveAddress);
	}

}
