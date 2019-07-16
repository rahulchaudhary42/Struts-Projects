package com.bridgelabz.dao;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bridgelabz.pojo.User;
import com.bridgelabz.utility.HibernateUtil;

public class UserServcieDaoImp implements IUserServiceDao
{
	
	private static UserServcieDaoImp userServcieDaoImp;

	private UserServcieDaoImp() {

	}

	public static synchronized UserServcieDaoImp getinstance() {
		if (userServcieDaoImp == null) {
			userServcieDaoImp = new UserServcieDaoImp();
			return userServcieDaoImp;
		}
		return userServcieDaoImp;
	}

	public String register(User user) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		return user.getName();
	}

	public Boolean login(String email, String password) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String sql = " from User u where u.email=:email and u.password=:password";
		Query query = session.createQuery(sql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		if (list.size() > 0) {
			session.close();
			return true;
		}
		session.close();
		return false;
	}

}
