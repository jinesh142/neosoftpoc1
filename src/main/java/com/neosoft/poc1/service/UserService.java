package com.neosoft.poc1.service;

import java.util.List;

import com.neosoft.poc1.model.User;

public interface UserService extends CRUDService<User> {

	List<User> filferByName(String searchParam);

	List<User> sortByDobAndJoinDate();
	
	boolean isUserExsit(Long id);
}
