package com.luv2code.springdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.luv2code.springdemo.entity.User;
import com.luv2code.springdemo.user.CrmUser;

public interface UserService extends UserDetailsService {

	User findUserByName(String username);
	
	void save(CrmUser crmUser);
}
