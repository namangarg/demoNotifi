package com.notifi.dao;

import java.util.ArrayList;
import java.util.List;
import com.notifi.model.User;
import com.notifi.model.UserInfo;

public interface UserDao {

	ArrayList<String> save(User user);

	User get(long id);

	List<User> list();

	void delete(long id);

	UserInfo getUserInfo(String phonenumber);

}