package org.zerock.agentboard.dto;

public class FileDTO {
	int no;
	String fileName;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "FileDTO [no=" + no + ", fileName=" + fileName + "]";
	}
	
	
	
}
