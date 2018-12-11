package org.zerock.member.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class LoginDTO {

	String email, pw, name, hp, addr, seller_name;
	int age, money, grade;
	boolean login;
	Date lastLog, joinDate;
	MultipartFile photo;
	String user_authCode;
	boolean user_authStatus;
	
	
	

	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public boolean isUser_authStatus() {
		return user_authStatus;
	}
	public void setUser_authStatus(boolean user_authStatus) {
		this.user_authStatus = user_authStatus;
	}
	public String getUser_authCode() {
		return user_authCode;
	}
	public void setUser_authCode(String user_authCode) {
		this.user_authCode = user_authCode;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	public boolean getLogin() {
		return login;
	}
	public void setLogin(int login) {
		if(login == 1) {
			this.login = true;			
		}else {
			this.login = false;
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getLastLog() {
		return lastLog;
	}
	public void setLastLog(Date lastLog) {
		this.lastLog = lastLog;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", pw=" + pw + ", name=" + name + ", hp=" + hp + ", addr=" + addr
				+ ", sellerName=" + seller_name + ", age=" + age + ", money=" + money + ", grade=" + grade + ", login="
				+ login + ", lastLog=" + lastLog + ", joinDate=" + joinDate + ", photo=" + photo + ", user_authCode="
				+ user_authCode + ", user_authStatus=" + user_authStatus + "]";
	}
	
	
	
	
}
