package com.budongsan.news.service;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.budongsan.news.craw.NewsCraw;
import com.budongsan.news.dao.NewsDAO;
import com.budongsan.news.dto.NewsDTO;
import com.budongsan.news.page.Criteria;

@Service
public class NewsService {

	@Inject
	private NewsDAO dao;
	
	private NewsCraw myCraw = new NewsCraw();
	
	//부동산 뉴스 리스트
	public List<NewsDTO> list(Criteria cri) throws IOException, ParseException {	
		System.out.println("list.service");
		dao.craw();
		dao.delete();
		cri.setTotalCount(dao.getTotalCount(cri));
		cri.calcData();
		
		return dao.list(cri); 
		}
	
	//글보기 + 조회수1증가
		public NewsDTO view(Integer no, boolean isView) {
			System.out.println("view.service");
			if(isView) dao.increase(no);
			return dao.view(no);
		}
		
	
		
}
