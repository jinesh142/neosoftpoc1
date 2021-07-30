package com.neosoft.poc1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.poc1.model.User;
import com.neosoft.poc1.repository.UserRepository;
import com.neosoft.poc1.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public List<User> filferByName(String searchParam) {
		return userRepository.searchByNameOrPincode(searchParam);
	}

	@Override
	public List<User> sortByDobAndJoinDate() {
		return userRepository.sortUserByDobAndJoinDate();
	}

	public boolean isEsxit(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public boolean isUserExsit(Long id) { 
		return userRepository.existsById(id);
	}
}
