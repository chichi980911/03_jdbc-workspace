package com.kh.model.vo;

import java.sql.Date;

public class PhoneNoteMenu {
	

	private int userno;
	private String userName;
	private int age;
	private String address;
	private String phone;
	private Date enrollDate;

	
	public PhoneNoteMenu() {
		
	}


	public int getUserno() {
		return userno;
	}


	public void setUserno(int userno) {
		this.userno = userno;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	public PhoneNoteMenu(int userno, String userName, int age, String address, String phone, Date enrollDate) {
		super();
		this.userno = userno;
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.enrollDate = enrollDate;
	}


	public PhoneNoteMenu(String userName, int age, String address, String phone) {
		super();
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}


	
	@Override
	public String toString() {
		return "PhoneNoteMenu [userno=" + userno + ", userName=" + userName + ", age=" + age + ", address=" + address
				+ ", phone=" + phone + ", enrollDate=" + enrollDate + "]";
	}

	
	
}
