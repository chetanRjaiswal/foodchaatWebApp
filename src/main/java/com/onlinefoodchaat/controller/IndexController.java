package com.onlinefoodchaat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController 
{
  
	@RequestMapping("/index")
	public String index()
	{
	   return "index"; 
	}
	
	@RequestMapping("/signUp")
	public String signUp()
	{
		return "signup";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/userSignUp")
	public String userSignUp()
	{
		return "UserSignUp";
	}
	
	@RequestMapping("/userLogin")
	public String userLogin()
	{
		return "userLogin";
	}
	


}

