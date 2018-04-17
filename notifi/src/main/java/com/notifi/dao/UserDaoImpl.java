package com.notifi.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.notifi.model.Shout;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.notifi.model.User;
import com.notifi.model.UserInfo;
import java.util.Base64.Decoder;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
    public void update(int userId, UserInfo userInfo) {
    	Session session = sessionFactory.getCurrentSession();
    	try{
			//Criteria criteria = session.createCriteria(User.class);
			//criteria.add(Restrictions.eq("userId", userId));
			//User user = (User) criteria.uniqueResult();
			UserInfo userInfo1 = (UserInfo)session.byId(UserInfo.class).load(userId);
		    userInfo1.setGender(userInfo.getGender());
			userInfo1.setName(userInfo.getName());
			userInfo1.setProfilePic(userInfo.getProfilePic());
    		session.update(userInfo1);
    	}
    	catch(Exception e){
    		throw e;
    	}
    }

	@Transactional
	public void save(User user) throws UnsupportedEncodingException {
		Session session = sessionFactory.getCurrentSession();
		try{
			AWSCredentials credentials = new BasicAWSCredentials("*****", "*********");
			final AmazonS3Client client = new AmazonS3Client(credentials);
			byte[] bI = java.util.Base64.getDecoder().decode(user.getUserInfo().getProfilePic());
			InputStream fis = new ByteArrayInputStream(bI);
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(bI.length);
			metadata.setContentType("image/png");
			metadata.setCacheControl("public, max-age=31536000");
			client.putObject("notifiimages", user.getPhonenumber(), fis, metadata);
			String url = "https://s3.eu-central-1.amazonaws.com/notifiimages/";
			//client.putObject("notifiimages","User1",file );
			User user1 = new User(user.getPhonenumber());
			//Integer id = (Integer)sessionFactory.getCurrentSession().save(user1);
			String name = user.getUserInfo().getName();
			String profilePic = url + URLEncoder.encode(user.getPhonenumber(), "UTF-8");
			String gender = user.getUserInfo().getGender();
			UserInfo userInfo = new UserInfo(name, gender, profilePic);
			userInfo.setUser(user1);
			sessionFactory.getCurrentSession().save(userInfo);
		}
		catch(Exception e){
			throw e;
		}
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

	@Transactional
	public void createShout(String phonenumber, Shout shout) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("phonenumber", phonenumber));
			User user = (User) criteria.uniqueResult();
			Set<Shout> hs = user.getShouts();
			hs.add(shout);
			shout.setUser(user);
			sessionFactory.getCurrentSession().save(shout);
		}
		catch(Exception e){
			throw e;
		}
	}
}