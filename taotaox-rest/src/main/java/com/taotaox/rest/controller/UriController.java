package com.taotaox.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UriController {
	
	@RequestMapping(value = {"/index", "/home", "/index.html", "/"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
