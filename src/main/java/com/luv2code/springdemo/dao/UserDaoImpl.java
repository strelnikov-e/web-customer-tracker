package com.luv2code.springdemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	@Qualifier(value = "securitySessionFactory")
	SessionFactory securitySessionFactory;
	
	Logger logger = Logger.getLogger(getClass());

	@Override
	public User findByUsername(String userName) {
		// get the current hibernate session
		Session currentSession = securitySessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", userName);
		User user = null;
		
		try {
			user = theQuery.getSingleResult();
			logger.info("\n****** " + user.getPassword());
		} catch (Exception e) {
			logger.info("\n*********** inside daoImpl exception");
			user = null;
		}
		return user;
	}

	@Override
	public void save(User user) {
		Session currentSession = securitySessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(user);

	}

}
