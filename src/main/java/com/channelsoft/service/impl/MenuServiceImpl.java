/**
 * 
 */
package com.channelsoft.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.fortsoft.genericdata.service.GenericService;
import ro.fortsoft.genericdata.utils.exception.AppException;

import com.channelsoft.exception.ServiceException;
import com.channelsoft.po.Menu;
import com.channelsoft.po.Role;
import com.channelsoft.po.UserInfo;
import com.channelsoft.service.IMenuService;
import com.dexcoder.dal.JdbcDao;

/**
 * 菜单信息service
 * 
 * @author tenanty
 */
@Service 
@Transactional
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private JdbcDao jdbcDao;
	@Autowired
	private GenericService genericService;


	/**
	 * 根据用户id查询其所属角色下的菜单
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Menu> findMenusByUserId(String userId) throws ServiceException {
		List<Menu> allMenus = new ArrayList<Menu>();
		try {
			UserInfo userInfo = genericService.get(UserInfo.class, userId);
			if(userInfo.getRoles().size()==1){
				allMenus.addAll(userInfo.getRoles().get(0).getMenus());
			}else{
				//多角色,需要合并
				List<String> menuIds = new ArrayList<String>();
				for(Role r:userInfo.getRoles()){
					for(Menu m:r.getMenus()){
						if(menuIds.contains(m.getMenuId())){
							continue;
						}else{
							menuIds.add(m.getMenuId());
							allMenus.add(m);
						}
					}
				}
				//重排序
				Collections.sort(allMenus, new Comparator<Menu>(){
					
					@Override
					public int compare(Menu o1, Menu o2) {
						return o1.getMenuOrder().compareTo(o2.getMenuOrder());
					}
					
				});
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AppException e) {
			e.printStackTrace();
		}
		return allMenus;
	}

}
