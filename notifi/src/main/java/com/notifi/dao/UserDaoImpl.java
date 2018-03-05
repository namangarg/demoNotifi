package com.notifi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;

import com.google.gson.Gson;
import com.notifi.model.HibernateUtil;
import com.notifi.model.User;
import com.notifi.model.UserInfo;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<String> save(User user) {
		ArrayList<String> list = new ArrayList<String>();
		return list;

	}

	// public List<User> list() {
	// Session session = sessionFactory.getCurrentSession();
	// CriteriaBuilder cb = session.getCriteriaBuilder();
	// CriteriaQuery<User> cq = cb.createQuery(User.class);
	// Root<User> root = cq.from(User.class);
	// cq.select(root);
	// Query<User> query = session.createQuery(cq);
	// return query.getResultList();
	// boolean userExist;
	// List<UserInfo> usersInfo;
	// SessionFactory sf = HibernateUtil.getSessionFactory();
	// Session session = sf.openSession();
	// session.beginTransaction();
	// Criteria criteria = session.createCriteria(User.class);
	// criteria.add(Restrictions.eq("phonenumber", phonenumber));
	// List<User> users = criteria.list();
	// if(users.size()==0){
	// usersInfo = null;
	// userExist = false;
	// }
	// else{
	// Criteria criteria1 = session.createCriteria(UserInfo.class);
	// criteria1.add(Restrictions.eq("userId", users.get(0).getUserId()));
	// usersInfo= criteria1.list();
	// userExist = true;
	// }
	// session.getTransaction().commit();
	// session.close();
	// if(userExist){
	// Gson gson = new Gson();
	// System.out.println("My ans is "+ gson.toJson(usersInfo.get(0)));
	// return usersInfo.get(0).toString();
	// }
	// else{
	// return "";
	// }
	// }
	// return users;
	// }

	@Transactional
	public List<User> list() {
		Session s = sessionFactory.getCurrentSession();
		Criteria criteria = s.createCriteria(User.class);
		List<User> users = criteria.list();
		return users;
	}

	private boolean isValidPassword(String password) {
		Pattern pattern = null;
		final Matcher matcher;
		pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$");
		matcher = pattern.matcher(password);
		return matcher.matches();

	}

	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public UserInfo getUserInfo(String phonenumber) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("phonenumber", phonenumber));
		User user = (User) criteria.uniqueResult();
		if (user == null) {
			return null;
		} else {
			return user.getUserInfo();
		}
	}
}