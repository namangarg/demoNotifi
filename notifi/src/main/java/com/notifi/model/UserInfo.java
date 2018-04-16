package com.notifi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfo {
	
	private int userId;
	private String gender;
	private String name;
	private String profilePic;
	
	@JsonIgnore
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public UserInfo() {
		
	}
	
	public UserInfo(String name, String gender, String profilePic ) {
		this.name = name;
		this.gender = gender;
		this.profilePic = profilePic;
	}
	
	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	@Override
	public String toString() {
		return "{userId=" + userId + ", gender=" + gender + ", name=" + name + "}";
	}
	
	
	

}
