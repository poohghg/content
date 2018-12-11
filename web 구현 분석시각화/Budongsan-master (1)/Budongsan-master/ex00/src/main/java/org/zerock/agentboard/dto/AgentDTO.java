package org.zerock.agentboard.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class AgentDTO {

	private int no;
	private String title, type, content, addr1, writer, tel;
	private Date writeDate;
	private int hit;
	
	// 첨부파일 하나를 받는 변수
	private MultipartFile file1;

	// 첨부파일명을 저장하는 변수
	private String fileName;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "AgentDTO [no=" + no + ", title=" + title + ", type=" + type + ", content=" + content + ", addr1="
				+ addr1 + ", writer=" + writer + ", tel=" + tel + ", writeDate=" + writeDate + ", hit=" + hit
				+ ", file1=" + file1 + ", fileName=" + fileName + "]";
	}

	


	
	
}