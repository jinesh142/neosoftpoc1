package com.neosoft.poc1.controller;
 
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.poc1.exception.UserNotFoundException;
import com.neosoft.poc1.model.User;
import com.neosoft.poc1.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user/")
public class UserController { 
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("get_all_users")
	public ResponseEntity<List<User>> getAllUser() {
		logger.info("Methods in  UserController : getAllUser called");
		try { 
			return new ResponseEntity<>(userServiceImpl.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("get_user_by_id")
	public ResponseEntity<User> getUserById(@RequestParam long id) {

		logger.info("Methods in  UserController : getUserById called");
		Optional<User> userData = userServiceImpl.getById(id); 

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			throw new UserNotFoundException("No User found with Id : " + id);
		}

	}

	@PostMapping("add_user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		logger.info("Methods in  UserController : addUser called");
		try {
			User retUser = userServiceImpl.save(user);
			return new ResponseEntity<>(retUser, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("update_user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Methods in  UserController : updateUser called");
		Optional<User> userData = userServiceImpl.getById(id);
		if (userData.isPresent()) {
			return new ResponseEntity<>(userServiceImpl.save(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("hard_delete/{id}")
	public ResponseEntity<HttpStatus> hardDeleteofUser(@PathVariable("id") long id) {
		logger.info("Methods in  UserController : hardDeleteofUser called");
		try {
			userServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("soft_delete/{id}")
	public ResponseEntity<HttpStatus> softDeleteofUser(@PathVariable("id") long id) {
		logger.info("Methods in  UserController : softDeleteofUser called");
		Optional<User> userData = userServiceImpl.getById(id); 
		if (userData.isPresent()) {
			User user = userData.get();
			user.setDeletedFlag(true);
			userServiceImpl.save(user);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("search_by_name_or_pincode")
	public ResponseEntity<List<User>> searchByAnyValue(@RequestParam String searchParam) {
		logger.info("Methods in  UserController : searchByAnyValue called");
		try { 
			return new ResponseEntity<>(userServiceImpl.filferByName(searchParam), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("get_all_users_sorting_by_dob_and_doj")
	public ResponseEntity<List<User>> getSortedUsers() {
		logger.info("Methods in  UserController : getSortedUsers called");
		try { 
			return new ResponseEntity<>(userServiceImpl.sortByDobAndJoinDate(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
