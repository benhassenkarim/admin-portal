package com.adminportal.adminportal.service;

import java.util.Set;

import com.adminportal.adminportal.domain.User;

import com.adminportal.adminportal.domain.security.UserRole;



public interface UserService {
	
	
	
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	User save(User user);
}
