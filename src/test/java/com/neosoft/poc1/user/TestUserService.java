package com.neosoft.poc1.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.neosoft.poc1.model.User;
import com.neosoft.poc1.service.impl.UserServiceImpl;

//@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestUserService {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Test
	@Order(1)
	public void testCreate() {
		User p = new User();
		p.setUserId(3L);
		p.setFirstName("Deba");
		p.setLastName("Fantastic");
		p.setPincode("909012");
		p.setAddress("BBSR");
		p.setAge(20);
		p.setCity("talcher");
		p.setCountry("India");
		p.setDob(new Date("2021/07/12"));
		p.setGender("Male");
		p.setJoiningDate(new Date("2021/07/12"));
		p.setMaritalStatus("Male");
		p.setMobileNo("9090808080");
		p.setStatus("Active");
		userServiceImpl.save(p);
		assertNotNull(userServiceImpl.getById(3L).get());
	}

	@Test
	@Order(2)
	public void testReadAll() {
		List<User> list = userServiceImpl.getAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead() {
		User product = userServiceImpl.getById(3L).get();
		assertEquals("Deba", product.getFirstName());
	}

	@Test
	@Order(4)
	public void testUpdate() {
		User p = userServiceImpl.getById(3L).get();
		p.setAge(25);
		userServiceImpl.save(p);
		assertNotEquals(20, userServiceImpl.getById(3L).get().getAge());
	}

	@Test
	@Order(5)
	public void testDelete() {
		userServiceImpl.delete(3L);
		assertThat(userServiceImpl.isUserExsit(3L)).isFalse();
	}

}
