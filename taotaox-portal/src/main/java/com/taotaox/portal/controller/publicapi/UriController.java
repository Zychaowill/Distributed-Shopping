package com.taotaox.portal.controller.publicapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UriController {

	@RequestMapping("/index")
	public String showIndex() throws Exception {
		return "index";
	}
}
