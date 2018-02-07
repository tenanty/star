/**
 * 
 */
package com.channelsoft.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.channelsoft.po.Menu;
import com.channelsoft.po.UserInfo;
import com.channelsoft.service.IMenuService;
import com.channelsoft.service.IUserInfoService;

/**
 * 菜单
 * 
 * @author cy
 */
@Controller
@RequestMapping(value="/edition")
public class MenuController extends BaseController{
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IUserInfoService userInfoService;
	
	@RequiresUser
	@RequestMapping(value = "/index")
	public String mainPage() {
		return "main";
	}
	
	@RequiresUser
	@RequestMapping(value="/loadMenus")
	@ResponseBody
	public List<Menu> loadMenus(){
		logger.info("凭证:"+SecurityUtils.getSubject().getPrincipal());
		UserInfo userInfo = null;
		if(SecurityUtils.getSubject().isRemembered()){
			userInfo = userInfoService.findUserInfo(new UserInfo(SecurityUtils.getSubject().getPrincipal().toString()));
		}else{
			userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
		}
		return menuService.findMenusByUserId(userInfo.getId());
	}
	
}
