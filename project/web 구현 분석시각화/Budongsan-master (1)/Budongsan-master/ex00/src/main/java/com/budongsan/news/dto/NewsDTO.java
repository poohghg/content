package com.budongsan.news.dto;

import java.util.Date;

public class NewsDTO {

	int no;
	String title,content,writer;
//	Date writeDate;
	String writeDate;
	int hit;
	String img;
	public NewsDTO() {
		
	}
	
	// 작성일 date 용
//	public NewsDTO(String title, String content, String writer, Date writeDate) {
//		this.title = title;
//		this.content = content;
//		this.writer = writer;
//		this.writeDate = writeDate;
//	
//	}
	public NewsDTO(String title, String content, String writer, String writeDate,String img) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.img = img;
	}

	//writeDate 코딩 전 테스트용 생성자
	public NewsDTO(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	
//	public Date getWriteDate() {
//		return writeDate;
//	}
//
//	public void setWriteDate(Date writeDate) {
//		this.writeDate = writeDate;
//	}


	}

	public String getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}


	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}


	@Override
	public String toString() {
		return "NewsDTO [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", writeDate="
				+ writeDate + ", hit=" + hit + "]";
	}
	
	
	
}
