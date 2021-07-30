package com.neosoft.poc1.user;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.poc1.controller.UserController;
import com.neosoft.poc1.model.User;
import com.neosoft.poc1.service.impl.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class TestUserController {

	@MockBean
	UserServiceImpl userServiceImpl;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	/**
	 * for test all user
	 * 
	 * @throws Exception
	 */
	@Test
	public void testfindAllUser() throws Exception {
		User user = new User(1L, "jinesh", "Behera");
		List<User> userList = Arrays.asList(user);

		Mockito.when(userServiceImpl.getAll()).thenReturn(userList);

		mockMvc.perform(MockMvcRequestBuilders.get("/user/get_all_users"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("jinesh")));
	}

	/**
	 * get user by id
	 * 
	 * @throws Exception
	 */
	@Test
	public void getUserByIdTest() throws Exception {
		Mockito.when(userServiceImpl.getById(1L)).thenReturn(java.util.Optional.of(new User(1l, "jinesh", "Behera")));

		mockMvc.perform(MockMvcRequestBuilders.get("/user/get_user_by_id?id=1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("jinesh")));
	}

	/**
	 * add user
	 * 
	 * @throws Exception
	 */
	@Test
	public void addUserTest() throws Exception {
		User user = new User(1l, "jinesh", "Behera");

		Mockito.when(userServiceImpl.save(user)).thenReturn(user);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/add_user")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(user));

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("jinesh")));
	}

	/**
	 * update User success case
	 */

	@Test
	public void updateUser_success() throws Exception {
		User user = User.builder().userId(11l).firstName("Mahesh").lastName("Behera").build();

		Mockito.when(userServiceImpl.getById(11l))
				.thenReturn(Optional.of(User.builder().userId(11l).firstName("Mahesh").lastName("Behera").build()));
		Mockito.when(userServiceImpl.save(user)).thenReturn(user);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/user/update_user/11")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(user));

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Mahesh")));
	}

	/**
	 * update User failed case
	 */

	@Test
	public void updateUser_fails() throws Exception {
		User user = User.builder().userId(20l).firstName("Mahesh").lastName("Behera").build();
		Mockito.when(userServiceImpl.getById(20l)).thenReturn(Optional.ofNullable(null));
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/user/update_user/20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(user));

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	/**
	 * for test delete success
	 */
	@Test
	public void deleteUserById_success() throws Exception {
		Mockito.when(userServiceImpl.getById(3l))
				.thenReturn(Optional.of(User.builder().userId(11l).firstName("Mahesh").lastName("Behera").build()));

		mockMvc.perform(MockMvcRequestBuilders.delete("/user/soft_delete/3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
/**
 * for  test delete failure
 * @throws Exception
 */
	@Test
	public void deleteUserById_notFound() throws Exception {
		Mockito.when(userServiceImpl.getById(5l)).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.delete("/user/soft_delete/5").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
/**
 * for hard delete
 * @throws Exception
 */
	@Test
	public void hardDeleteUserById_success() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.delete("/user/hard_delete/9").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void searchByAnyValueTest() throws Exception {
		User user = new User(7L, "Rajesh", "Ram");
		List<User> userList = Arrays.asList(user);

		Mockito.when(userServiceImpl.filferByName("Rajesh")).thenReturn(userList);

		mockMvc.perform(MockMvcRequestBuilders.get("/user/search_by_name_or_pincode?searchParam=Rajesh"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("jinesh")));
	}
	
	@Test
	public void getSortedUsersTest() throws Exception {
		User user = new User(12L, "Ramesh", "Ram");
		User user2 = new User(18L, "Harish", "Risi");
		List<User> userList = Arrays.asList(user,user2);

		Mockito.when(userServiceImpl.filferByName("Rajesh")).thenReturn(userList);

		mockMvc.perform(MockMvcRequestBuilders.get("/user/get_all_users_sorting_by_dob_and_doj"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("jinesh")));
	}
}
