/**
 * 
 */
package com.channelsoft.service;

import java.util.List;

import com.channelsoft.exception.ServiceException;
import com.channelsoft.po.Permission;
import com.channelsoft.po.Role;
import com.channelsoft.po.UserInfo;

/**
 * 用户信息接口
 * 
 * @author tenanty
 *
 */
public interface IUserInfoService {
	/**
	 * 校验用户信息
	 * @param userInfo
	 * @return
	 */
	public UserInfo findUserInfo(UserInfo userInfo) throws ServiceException;
	/**
	 * 按照用户名查询拥有的角色信息
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> queryRolesByUserName(String userName) throws ServiceException;
	/**
	 * 按照角色ID查询角色拥有的权限信息
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	public List<Permission> queryPermissionByRoleId(String roleId) throws ServiceException;

}
