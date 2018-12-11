package com.budongsan.news.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.budongsan.news.page.Criteria;
import com.budongsan.news.service.NewsService;

@Controller
@RequestMapping(value="/news/")
public class NewsController {

	@Inject
	
	private NewsService service;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void list(Model model,Criteria cri) throws IOException, ParseException {
		System.out.println("list.controller");
		model.addAttribute("list", service.list(cri));
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	public void view(Model model, int no) {
		System.out.println("view.controller");
		model.addAttribute("dto",service.view(no,true));
		service.view(no,false);
	}
	
}
