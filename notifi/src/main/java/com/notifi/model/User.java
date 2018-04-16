package com.notifi.model;

import java.util.HashSet;
import java.util.Set;

public class User {

	private int userId;
	//private int otp;
	private String phonenumber;
	private UserInfo userInfo;
	private Set<Shout> shouts =
			new HashSet<Shout>(0);

	public Set<Shout> getShouts() {
		return shouts;
	}

	public void setShouts(Set<Shout> shouts) {
		this.shouts = shouts;
	}


	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

//	public int getOtp() {
//		return otp;
//	}

//	public void setOtp(int otp) {
//		this.otp = otp;
//	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public User() {

	}

	public User(String phonenumber) {
		//this.otp = otp;
		this.phonenumber = phonenumber;
	}

}
