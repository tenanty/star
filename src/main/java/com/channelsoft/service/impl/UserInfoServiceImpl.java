/**
 * 
 */
package com.channelsoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.fortsoft.genericdata.service.GenericService;

import com.channelsoft.exception.ServiceException;
import com.channelsoft.po.Permission;
import com.channelsoft.po.Role;
import com.channelsoft.po.UserInfo;
import com.channelsoft.service.IUserInfoService;

/**
 * 用户信息Service
 * 
 * @author tenanty
 */
@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {
	
	@Autowired
	private JdbcOperations jdbcTemplate;
	@Autowired
	private GenericService genericService;
	
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 校验用户信息是否存在
	 * 用户信息不存在时返回null
	 */
	@Override
	public UserInfo findUserInfo(UserInfo userInfo) throws ServiceException {
		try {
			return genericService.getUnique(UserInfo.class, userInfo);
		} catch (Exception e) {
			throw new ServiceException("按照查询条件:["+userInfo.toString()+"]查询用户信息时发生异常",e);
		}
	}

	/**
	 * 按照用户名查询拥有的角色信息
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Role> queryRolesByUserName(String userName) throws ServiceException  {
		try {
			List<Role> roles = new ArrayList<Role>();
			UserInfo userInfo = genericService.getUnique(UserInfo.class, new UserInfo(userName));
			roles.addAll(userInfo.getRoles());
			return roles;
		} catch (Exception e) {
			throw new ServiceException("查询用户:["+userName+"]角色信息时发生异常",e);
		}
	}

	/**
	 * 按照角色ID查询角色拥有的权限信息
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Permission> queryPermissionByRoleId(String roleId) throws ServiceException  {
		try {
			List<Permission> permissions = new ArrayList<Permission>();
			Role role = genericService.get(Role.class, roleId);
			permissions.addAll(role.getPermission());
			return permissions;
		} catch (Exception e) {
			throw new ServiceException("查询角色ID:["+roleId+"]包含的权限信息时发生异常",e);
		}
	}

}
