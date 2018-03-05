package com.notifi.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

	public static void main(String[] args) {
		createJson(getUserByPhonenumber("+9461303393"));

		
	}
		
		public static void createJson(String data){
			String isExist;
			if(data.equals("")){
				isExist = "false";
			}
			else{
				isExist = "true";
			}
		
		String myJson = "{" + "userData" +  ":" + "{" + "\n" +  data + "," + "\n"+ "isExist"+ ":" + isExist + "}";
		System.out.println(myJson);
		}

	public static String getUserByPhonenumber(String phonenumber) {
		boolean userExist;
		List<UserInfo> usersInfo;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("phonenumber", phonenumber));
		List<User> users = criteria.list();
		if(users.size()==0){
			usersInfo = null;
			userExist = false;
		}
		else{
			Criteria criteria1 = session.createCriteria(UserInfo.class);
			criteria1.add(Restrictions.eq("userId", users.get(0).getUserId()));
			usersInfo= criteria1.list();
			userExist = true;
		}
		session.getTransaction().commit();
		session.close();
		if(userExist){
			Gson gson = new Gson();
			System.out.println("My ans is "+ gson.toJson(usersInfo.get(0)));
			return usersInfo.get(0).toString();
		}
		else{
			return "";
		}
	}

	public boolean saveUser() {
		UserInfo userInfo = new UserInfo("Male", "Naman");

		User user = new User(123456, "+9461303393");
		user.setUserInfo(userInfo);
		userInfo.setUser(user);
		// for (User u : users) {
		// System.out.println(u.getPhonenumber() + " , " + u.getOtp() + ", " +
		// u.getUserInfo().getGender());
		// }
		return true;

	}
}