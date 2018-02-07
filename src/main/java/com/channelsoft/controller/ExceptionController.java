/**
 * 
 */
package com.channelsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常信息处理controller
 * 
 * @author tenanty
 */
@Controller
public class ExceptionController {
	
	@RequestMapping("/error")
	public String defaultError(){
		return "error";
	}
	
	@RequestMapping("/testError")
	public String testError() throws Exception{
		throw new Exception();
	}

}
