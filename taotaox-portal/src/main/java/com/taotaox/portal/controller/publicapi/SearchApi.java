package com.taotaox.portal.controller.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.portal.service.SearchService;

@RestController
public class SearchApi {

	@Autowired
	private SearchService searchService;
	
	public String search(@RequestParam("q") String queryString, 
			@RequestParam(defaultValue = "1") Integer page,
			Model model) {
		
		return "search";
	}
}
