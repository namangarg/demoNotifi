package com.notifi.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.notifi.model.Shout;
import com.notifi.model.User;
import com.notifi.model.UserInfo;

public interface UserDao {

	User get(long id);

	List<User> list();

	void delete(long id);

	UserInfo getUserInfo(String phonenumber);

	void save(User user) throws UnsupportedEncodingException;

	void update(int id, UserInfo userInfo);

	void createShout(String phonenumber, Shout shout);

}