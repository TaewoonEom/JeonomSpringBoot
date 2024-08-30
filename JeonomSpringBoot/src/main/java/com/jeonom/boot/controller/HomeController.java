package com.jeonom.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

//	@ResponseBody
//	@RequestMapping(value="/", method=RequestMethod.GET)
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}
}
