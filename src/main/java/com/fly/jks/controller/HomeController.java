package com.fly.jks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//系统首页模块
	
	@RequestMapping(value={"/home"})		//配合web下<url-pattern>/</url-pattern>
	public String login(){
		
		return "/WEB-INF/pages/index.jsp";			//首页，删除根目录下index.jsp，否则上面url将被拦截进不来
	}

    /**
	 * 01
     * 防止表单重复提交，这里要在提交表单后,进行重定向到另一个action
     * @return
     */
	@RequestMapping(value="/fmain")
	public String fmain(){
		return "redirect:/fmains";
	}

	/**
	 * 02
	 * 重定向后转跳到主页
	 * @return
	 */
	@RequestMapping(value="/fmains")
	public String fmains(){
		return "/WEB-INF/pages/home/fmain.jsp";
	}

	@RequestMapping(value="/title")
	public String title(){
		return "/WEB-INF/pages/home/title.jsp";
	}
	
	@RequestMapping(value="/left")
	public String left(){
		return "/WEB-INF/pages/home/left.jsp";
	}
	
	@RequestMapping(value="/main")
	public String main(){
		return "/WEB-INF/pages/home/olmsgList.jsp";			//首页转向留言板
	}
	
	//系统管理模块
	
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "/WEB-INF/pages/sysadmin/main.jsp";
	}
	
	@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/WEB-INF/pages/sysadmin/left.jsp";
	}
	
	//基础信息模块
	
	@RequestMapping("/baseinfoMain")
	public String baseinfoMain(){
		return "/WEB-INF/pages/baseinfo/main.jsp";
	}
	
	@RequestMapping("/baseinfoLeft")
	public String baseinfoLeft(){
		return "/WEB-INF/pages/baseinfo/left.jsp";
	}
	
	//货运管理模块
	
	@RequestMapping("/cargoMain")
	public String cargoMain(){
		return "/WEB-INF/pages/cargo/main.jsp";
	}
	
	@RequestMapping("/cargoLeft")
	public String cargoLeft(){
		return "/WEB-INF/pages/cargo/left.jsp";
	}
}
