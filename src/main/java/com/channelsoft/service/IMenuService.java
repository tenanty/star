/**
 * 
 */
package com.channelsoft.service;

import java.util.List;

import com.channelsoft.exception.ServiceException;
import com.channelsoft.po.Menu;

/**
 * 菜单信息接口
 * 
 * @author tenanty
 */
public interface IMenuService {
	/**
	 * 根据用户id查询其所属角色下的菜单
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<Menu> findMenusByUserId(String userId) throws ServiceException;

}
