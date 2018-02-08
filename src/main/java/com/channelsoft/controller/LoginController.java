package com.channelsoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.channelsoft.exception.ServiceException;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.channelsoft.po.UserInfo;
import com.channelsoft.service.IUserInfoService;
import com.dexcoder.commons.utils.EncryptUtils;

/**
 * 用户登录Controller
 * 
 * @author tenanty
 */
@Controller
public class LoginController {
	
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private IUserInfoService userInfoService;
	
	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login(){
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if(currentUser.isRemembered()||currentUser.isAuthenticated()){
				return "redirect:/edition/index";
			}
		} catch (Exception e) {
			logger.error("获取用户身份异常",e);
		}
		return "login";
	}
	@RequestMapping(value="/")
	public String index(){
		return "redirect:/login";
	}
	/**
	 * 返回登录页面,不会出现局部加载登录页面的情况
	 * @return
	 */
	@RequestMapping(value="/reload")
	public String reload(){
		return "reload";
	}
	
	
	/**
	 * 登录校验
	 * @param userInfo
	 * @param session
	 * @param request
	 * @param remember
	 * @return
	 */
    @RequestMapping("/dologin")
    @ResponseBody
    public String dologin(UserInfo userInfo, HttpSession session,HttpServletRequest request ,Integer remember) {
        Subject currentUser = SecurityUtils.getSubject();
        //密码改为前端加密,后端不再处理,加密算法可参考EncryptUtils
        UsernamePasswordToken token = new UsernamePasswordToken(
                userInfo.getUserName(), EncryptUtils.getMD5(userInfo.getUserPwd()));
        if(remember != null && remember ==1){
            token.setRememberMe(true);
        }else{
            token.setRememberMe(false);
        }
        if (currentUser.isAuthenticated()) {
        	return "0";
        }
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            logger.error(String.format("用户登录失败，userName:%s，password:%s,错误原因：%s", userInfo.getUserName(),userInfo.getUserPwd(),e.getMessage()));

            return "-1";
        }
        if (currentUser.isAuthenticated()) {
            userInfo = userInfoService.findUserInfo(new UserInfo(userInfo.getUserName()));
            SecurityUtils.getSubject().getSession().setAttribute("userInfo", userInfo);
            return "0";
        } else {
        	return "-1";
        }
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
    
    public static void main(String[] args) {
		System.out.println(EncryptUtils.getMD5("qctest"));
	}
    
}
