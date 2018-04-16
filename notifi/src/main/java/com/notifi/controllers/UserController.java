package com.notifi.controllers;

import java.util.HashMap;
import java.util.List;

import com.notifi.dao.UserDao;
import com.notifi.model.Shout;
import com.notifi.model.User;
import com.notifi.model.UserInfo;

import com.notifi.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/update/{userId}")
	public HashMap<String, Object> saveUser(@PathVariable int userId, @RequestBody UserInfo userInfo) {
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		try{
			userDao.update(userId,userInfo);
			resultData.put("success", "true");
			resultData.put("error", "false");
			resultData.put("message", "");
			//return "success";
		}
		catch(Exception e){
			resultData.put("success", "false");
			resultData.put("error", "true");
			resultData.put("message", "");
			//return e.getMessage();
		}
		finally {
				return resultData;
			}
		}

	@RequestMapping(method = RequestMethod.POST, value = "/user/create")
	public HashMap<String, Object> saveUser(@RequestBody User user) {
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		try{
			userDao.save(user);
			resultData.put("success", "true");
			resultData.put("error", "false");
			resultData.put("message", "");
		}
		catch(Exception e){
			resultData.put("success", "false");
			resultData.put("error", "true");
			resultData.put("message", "");
		}
		finally {
			return resultData;
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/{phonenumber}/shouts")
	public HashMap<String, Object> createShout(@PathVariable String phonenumber, @RequestBody Shout shout) {
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		try{
			userDao.createShout(phonenumber, shout);
			resultData.put("success", "true");
			resultData.put("error", "false");
			resultData.put("message", "");
		}
		catch(Exception e){
			resultData.put("success", "false");
			resultData.put("error", "true");
			resultData.put("message", "");
		}
		finally {
			return resultData;
		}

	}
}