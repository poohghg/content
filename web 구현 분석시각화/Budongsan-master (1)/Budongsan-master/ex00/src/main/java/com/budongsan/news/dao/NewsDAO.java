package com.budongsan.news.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.javassist.tools.reflect.Sample;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.budongsan.news.craw.NewsCraw;
import com.budongsan.news.dto.NewsDTO;
import com.budongsan.news.page.Criteria;

@Repository
public class NewsDAO {

	@Inject
	private SqlSession sql;

	private NewsCraw myCraw = new NewsCraw();
	
	// NewsMapper
	private String abs = "com.budongsan.mapper.NewsMapper.";

	// 부동산 뉴스 리스트
	public List<NewsDTO> list(Criteria cri) {
		System.out.println("list.dao");
		
		return sql.selectList(abs + "list", cri);
	}

	// 글보기 -글번호가 필요하다
	public NewsDTO view(Integer no) {
		System.out.println("view.dao");
		return sql.selectOne("com.budongsan.mapper.NewsMapper.view", no);
	}

	// 글보기 조회수1증가
	public void increase(Integer no) {
		System.out.println("increase.dao");
		sql.update("com.budongsan.mapper.NewsMapper.increase", no);
	}

	public Integer getTotalCount(Criteria cri) {
		System.out.println("getTotalCount.dao");
		return sql.selectOne(abs + "totalCount", cri);

	}
	public void delete() {
		sql.delete(abs+"delete");
	}

	public void craw() throws IOException, ParseException, IndexOutOfBoundsException{
		System.out.println("craw.dao");
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> content = new ArrayList<String>();
		ArrayList<String> writer = new ArrayList<String>();
		ArrayList<String> writeDate = new ArrayList<String>();
		ArrayList<String> last = new ArrayList<String>();
		ArrayList<String> img = new ArrayList<String>();
		Map<String, ArrayList<String>> map = myCraw.craw();
		Map<String, ArrayList<String>> mapp = myCraw.last();

				
		title = map.get("title");
		content = map.get("content");
		writer = map.get("writer");
		writeDate = map.get("writeDate");
		last = mapp.get("lastWriteDate");
		img = map.get("img");

		
		System.out.println("============================================");
		System.out.println(title.size());
		System.out.println(content.size());
		System.out.println(writer.size());
		System.out.println(writeDate.size());
		System.out.println(img.size());

	
		int b = writeDate.size();
			for (int i = 0; i < title.size(); i++) {
				NewsDTO dto = new NewsDTO(title.get(i),content.get(i), writer.get(i), writeDate.get(i),img.get(i));
				sql.insert(abs + "craw", dto);	
			
			}
				}
			}				
	

	
		
		
	
