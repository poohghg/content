package com.budongsan.news.craw;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsCraw {

	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> content = new ArrayList<String>();
	ArrayList<String> writer = new ArrayList<String>();
	ArrayList<String> writeDate = new ArrayList<String>();
	ArrayList<String> lastWriteDate = new ArrayList<String>();
	ArrayList<String> img = new ArrayList<String>();
	// ArrayList<Date> writeDate = new ArrayList<Date>();

	Map<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> lastResult = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<Date>> result2 = new HashMap<String, ArrayList<Date>>();

	private String pageUrl = "http://realestate.daum.net/news/all?page=";

	public Map<String, ArrayList<String>> craw() throws IOException, ParseException, IndexOutOfBoundsException {
		for (int i = 1; i <= 1; i++) {
			Document doc = Jsoup.connect(pageUrl + i).get();
			// 1.제목
			Elements eTilte = doc.select("#live > ul > li > div > strong a");
			for (Element et1 : eTilte) {
				title.add(et1.text());
				System.out.println(et1.text());
			}

			// 2.컨텐트
			Elements eClink = doc.select("#live > ul > li > a");
			for (Element el2 : eClink) {
				Document doc2 = Jsoup.connect(el2.attr("abs:href")).get();
				Elements eContent = doc2.select("#dmcfContents > section");

				for (Element et2 : eContent) {
					content.add(et2.text());
					System.out.println("=============================");
					System.out.println(et2.text());
				}
			}

			// 3.작성자

			Elements eWriter = doc.select("#live > ul > li > div > p > span > span.source");

			for (Element et3 : eWriter) {
				writer.add(et3.text());

			}
			// 4.작성일

			Elements eWriteDate = doc.select("#live > ul > li > div > p > span > span.time");

			for (Element et4 : eWriteDate) {
				writeDate.add(et4.text());
			}


			// 이미지
			Elements ilink = doc.select("#live > ul > li > a");
			for (Element el5 : ilink) {
				Document doc5 = Jsoup.connect(el5.attr("abs:href")).get();
				Elements eImg = doc5.select("#dmcfContents > section > figure > img");
				if(eImg.size() == 0) {
					img.add("https://t1.daumcdn.net/daumtop_chanel/op/20170315064553027.png");
				} else {
					img.add(eImg.get(0).attr("abs:src"));
				}
			}

			
			result.put("title", title);
			result.put("content", content);
			result.put("writer", writer);
			result.put("writeDate", writeDate);
			result.put("img", img);

		} // page for문 종료

		return result;

	}


	public Map<String, ArrayList<String>> last() throws IOException {
		Document doc = Jsoup.connect(pageUrl + "1").get();
		Elements LWriteDate = doc.select("#live > ul > li:nth-child(1)> div > p > span > span.time");
		for (Element etl4 : LWriteDate) {
			lastWriteDate.add(etl4.text());
		}
		lastResult.put("lastWriteDate", lastWriteDate);

		return lastResult;
	}
}
