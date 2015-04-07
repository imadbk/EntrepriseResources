package com.imad.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            model.setViewName("security/login");
        } else {
            model.setViewName("redirect:/accueil");
        }

        return model;
	}
	
	@RequestMapping(value = "/accueil", method = {RequestMethod.GET, RequestMethod.POST})
	public String accueil(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "security/login";
        } else {
            return "accueil";
        }

	}
	
	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
	public String register(HttpServletRequest request){
		
        return "users/register";
	}
	
	@RequestMapping(value = "/reset", method = {RequestMethod.GET, RequestMethod.POST})
	public String reset(HttpServletRequest request){
		
        return "security/reset";
	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(){
		return "redirect:/j_spring_security_logout";
	}
	
	@RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
	public String contact(HttpServletRequest request){
		
        return "contact";
	}
	
}
