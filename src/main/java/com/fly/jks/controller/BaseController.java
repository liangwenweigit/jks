package com.fly.jks.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * spring封装有时间的controller需要继承这个类
 */
public abstract class BaseController {
	@InitBinder
	//此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
	//使用，需要转换日期的controller直接继承这个类即可
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
