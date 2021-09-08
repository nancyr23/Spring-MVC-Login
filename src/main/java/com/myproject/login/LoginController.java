package com.myproject.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.login.service.LoginService;

@Controller
public class LoginController 
{
	@RequestMapping("/login")
	public ModelAndView verifyUser(HttpServletRequest req, HttpServletResponse res)
	{
		ModelAndView mv = new ModelAndView();
		
		String uname = req.getParameter("uname");
		String pass = req.getParameter("pass");
		
		if(new LoginService().isValidUser(uname, pass))
		{
			mv.addObject("user", uname.toUpperCase());
			mv.setViewName("success.jsp");
		}
		else
		{
			mv.setViewName("index.jsp");
		}
		
		return mv;
	}
}
