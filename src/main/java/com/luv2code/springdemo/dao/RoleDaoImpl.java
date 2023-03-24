package com.luv2code.springdemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Role findRoleByName(String roleName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:rName", Role.class);
		theQuery.setParameter("rName", roleName);
		Role role = null;
		
		try {
			role = theQuery.getSingleResult();
		} catch (Exception e) {
			role = null;
		}
		return role;
	}

}
