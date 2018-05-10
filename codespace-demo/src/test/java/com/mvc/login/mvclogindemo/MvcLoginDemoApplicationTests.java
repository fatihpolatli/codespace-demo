package com.mvc.login.mvclogindemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mvc.login.config.MvcLoginDemoApplication;
import com.mvc.login.controller.RegistrationController;
import com.mvc.login.dto.UserDto;
import com.mvc.login.entity.User;
import com.mvc.login.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { MvcLoginDemoApplication.class })
@AutoConfigureMockMvc
public class MvcLoginDemoApplicationTests {

	@Autowired
	RegistrationController registrationController;

	@Autowired
	private MockMvc mockMvc;

	private final String BASE_URI = "http://localhost:8080/";

	@Test
	public void contextLoads() {

		assertThat(registrationController).isNotNull();
	}

	@Autowired
	IUserService userService;

	@Test
	public void userRegistrationTest() throws Exception {

		UserDto accountDto = new UserDto();
		accountDto.setFirstName("testUser");
		accountDto.setPassword("test");
		accountDto.setEmail("test1@test.com");

		userService.registerNewUserAccount(accountDto);

		User user = userService.findByUserName(accountDto.getFirstName());
		assertEquals(accountDto.getFirstName(), user.getUsername());
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/restful/test")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("this is test")));
	}

	@Test
	public void successfulRegistrationTest() throws Exception {

		getResponseForPasswordOk("test123", "test2@test.com", "Test200400.");

	}

	@Test
	public void shortNameTest() throws Exception {

		getResponseForPassword("te", "test3@test.com", "Test200400.");

	}
	
	@Test
	public void emailTest() throws Exception {

		getResponseForPassword("teest1234", "test3", "Test200400.");

	}
	
	@Test
	public void passwordTest() throws Exception {

		getResponseForPassword("teest1234", "test4@test.com", "Test20");

	}
	
	
	
	private void getResponseForPassword(String name, String email, String password) throws Exception {
		
		this.mockMvc.perform(post("/user/registration")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
	                    new BasicNameValuePair("firstName", name),
	                    new BasicNameValuePair("email", email),
	                    new BasicNameValuePair("password", password),
	                    new BasicNameValuePair("matchingPassword", password)
	            )))))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.error", is("error")));

	}

	private void getResponseForPasswordOk(String name, String email, String password) throws Exception {
		

		this.mockMvc.perform(post("/user/registration")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
	                    new BasicNameValuePair("firstName", name),
	                    new BasicNameValuePair("email", email),
	                    new BasicNameValuePair("password", password),
	                    new BasicNameValuePair("matchingPassword", password)
	            )))))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("success")));

	}

}
