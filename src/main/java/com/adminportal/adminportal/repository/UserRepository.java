package com.adminportal.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.adminportal.domain.User;

public interface UserRepository extends  CrudRepository<User, Long> {

	User findByUsername(String username);

}
