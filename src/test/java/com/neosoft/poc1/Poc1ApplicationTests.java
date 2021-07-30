package com.neosoft.poc1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.neosoft.poc1.controller.UserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Poc1ApplicationTests {
	@Autowired
	UserController userController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(userController).isNot(null);
	}
}
