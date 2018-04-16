package com.notifi.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.notifi.dao.UserDao;
import com.notifi.model.User;
import com.notifi.model.UserInfo;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
	public void save(User user) throws RuntimeException {
		try {
			//userDao.save(user);
		} catch (ConstraintViolationException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	public User get(long id) {
		return userDao.get(id);
	}

	public List<User> list() {
		return userDao.list();
	}


	@Transactional
	public void delete(long id) {
		userDao.delete(id);
	}

	public String update(long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
