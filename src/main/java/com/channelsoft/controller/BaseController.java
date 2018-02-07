/**
 * 
 */
package com.channelsoft.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller基类
 *
 * @author tenanty
 */
public abstract class BaseController {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	
	@ExceptionHandler(value={UnauthorizedException.class,AuthorizationException.class})  
    protected String exp(Exception ex,HttpServletRequest request) {  
        return "redirect:/reload";  
    }  

}
