package com.notifi.controllers;

import java.util.HashMap;
import java.util.List;

import com.notifi.dao.UserDao;
import com.notifi.model.User;
import com.notifi.model.UserInfo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/")
	public List<User> home() {
		List<User> listUsers = userDao.list();
		// ModelAndView model = new ModelAndView("home");
		// model.addObject("userList", listUsers);
		return listUsers;
	}

	@RequestMapping(value = "/user/{phonenumber}")
	public HashMap<String, Object> getUserByPhonenumber(@PathVariable String phonenumber) {
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		try {
			UserInfo listUserInfo = userDao.getUserInfo(phonenumber);
			resultData.put("userData", listUserInfo);
			if (listUserInfo != null) {
				resultData.put("isExist", "true");
				resultData.put("success", "true");
			} else {
				resultData.put("isExist", "false");
				resultData.put("success", "true");

			}
			return resultData;
		} catch (Exception e) {
			resultData.put("success", "false");
			return resultData;
		}

	}
}