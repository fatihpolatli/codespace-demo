package com.mvc.login.mvclogindemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Set;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.login.config.MvcLoginDemoApplication;
import com.mvc.login.controller.RegistrationController;
import com.mvc.login.dto.BalanceDto;
import com.mvc.login.dto.TransferDto;
import com.mvc.login.dto.UserDto;
import com.mvc.login.entity.MoneyTypes;
import com.mvc.login.entity.User;
import com.mvc.login.entity.UserAccount;
import com.mvc.login.entity.UserWithoutPassword;
import com.mvc.login.enums.AccountTransactionType;
import com.mvc.login.enums.MoneyTypesEnum;
import com.mvc.login.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { MvcLoginDemoApplication.class })
@AutoConfigureMockMvc
@WithMockUser(username = "test12345", password = "Test2000.", roles = { "ADMIN" })
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

	/*
	 * public void preLogin() throws ParseException, UnsupportedEncodingException,
	 * IOException, Exception {
	 * 
	 * System.out.println("PRE LOGIN");
	 * 
	 * this.mockMvc.perform(post("/login")
	 * .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	 * .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList( new
	 * BasicNameValuePair("username", "test1234"), new
	 * BasicNameValuePair("password", "Test200."), new BasicNameValuePair("login",
	 * "submit") ))))); }
	 */

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

	@Test
	public void getUserAccountsOK() throws Exception {

		this.mockMvc.perform(get("/restful/accounts/list").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.error", is(nullValue())));

	}
	
	@Test
	public void deleteAccount() throws Exception {


		 Set<UserAccount> accounts = userService.getAccounts();
			
		if (accounts.size() == 0 || accounts == null) {

			MoneyTypes m = new MoneyTypes();
			m.setId(MoneyTypesEnum.TRY.val());

			UserAccount account = new UserAccount();

			account.setMoneyType(m);

			User u = userService.addAcount(account);

			accounts = u.getAccounts();

		}

		UserAccount account2 = (UserAccount) accounts.toArray()[0];


		this.mockMvc
				.perform(post("/restful/accounts/delete").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(account2)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data", is(true)));
		
		System.out.println("it is over");

	}


	@Test
	public void addUserAccountOK() throws Exception {

		UserAccount a = new UserAccount();

		MoneyTypes m = new MoneyTypes();
		m.setId(MoneyTypesEnum.GBP.val());

		a.setMoneyType(m);

		this.mockMvc
				.perform(post("/restful/accounts/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(a)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data", is(true)));

	}

	@Test
	public void cannotAddMoreThanOneFromSameAccount() throws Exception {

		MoneyTypes m = new MoneyTypes();
		m.setId(MoneyTypesEnum.TRY.val());

		this.mockMvc.perform(
				post("/restful/accounts/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(m)));

		this.mockMvc
				.perform(post("/restful/accounts/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(m)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.error", is("error")));

	}
	
	@Test
	public void addBalance() throws Exception {

	
		Set<UserAccount> accounts = userService.getAccounts();
		
		
		if (accounts.size() == 0 || accounts == null) {

			MoneyTypes m = new MoneyTypes();
			m.setId(MoneyTypesEnum.TRY.val());

			UserAccount account = new UserAccount();

			account.setMoneyType(m);

			User u = userService.addAcount(account);

			accounts = u.getAccounts();

		}
		
		
		UserAccount accounts1 = (UserAccount) accounts.toArray()[0];
		
		Long addedValue = 150L;
		
		BalanceDto balance = new BalanceDto();
		balance.setAccount(accounts1);
		balance.setAmount(addedValue);
		balance.setType(AccountTransactionType.ADD);
		
		Long initialBalance = accounts1.getBalance();

		
		this.mockMvc
				.perform(post("/restful/accounts/balance").contentType(MediaType.APPLICATION_JSON).content(asJsonString(balance)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data", is(true)));
		
		/*Long newBalance = userService.getCurrentBalance(balance);
		
		Assert.isTrue((addedValue + initialBalance) == newBalance, "balance must be right");*/

	}
	
	@Test
	public void getCurrentBalance() throws Exception {

	
		Set<UserAccount> accounts = userService.getAccounts();
		
		
		if (accounts.size() == 0 || accounts == null) {

			MoneyTypes m = new MoneyTypes();
			m.setId(MoneyTypesEnum.TRY.val());

			UserAccount account = new UserAccount();

			account.setMoneyType(m);

			User u = userService.addAcount(account);

			accounts = u.getAccounts();

		}
		
		
		UserAccount accounts1 = (UserAccount) accounts.toArray()[0];
		
		Long addedValue = 150L;
		
		BalanceDto balance = new BalanceDto();
		balance.setAccount(accounts1);
		balance.setAmount(addedValue);
		balance.setType(AccountTransactionType.ADD);
		
		Long initialBalance = accounts1.getBalance();

		
		this.mockMvc
				.perform(post("/restful/accounts/currentBalance").contentType(MediaType.APPLICATION_JSON).content(asJsonString(balance)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(nullValue())));
		
		/*Long newBalance = userService.getCurrentBalance(balance);
		
		Assert.isTrue((addedValue + initialBalance) == newBalance, "balance must be right");*/

	}
	
	@Test
	public void getAccountHistory() throws Exception {

	
		Set<UserAccount> accounts = userService.getAccounts();
		
		
		if (accounts.size() == 0 || accounts == null) {

			MoneyTypes m = new MoneyTypes();
			m.setId(MoneyTypesEnum.TRY.val());

			UserAccount account = new UserAccount();

			account.setMoneyType(m);

			User u = userService.addAcount(account);

			accounts = u.getAccounts();

		}
		
		
		UserAccount accounts1 = (UserAccount) accounts.toArray()[0];
		
		Long addedValue = 150L;
		
		BalanceDto balance = new BalanceDto();
		balance.setAccount(accounts1);
		balance.setAmount(addedValue);
		balance.setType(AccountTransactionType.ADD);
		
		Long initialBalance = accounts1.getBalance();

		
		this.mockMvc
				.perform(post("/restful/accounts/history").contentType(MediaType.APPLICATION_JSON).content(asJsonString(balance)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(nullValue())));
		
		/*Long newBalance = userService.getCurrentBalance(balance);
		
		Assert.isTrue((addedValue + initialBalance) == newBalance, "balance must be right");*/

	}
	
	@Test
	public void getUserList() throws Exception {

	
		
		
		this.mockMvc
				.perform(get("/restful/user/list"))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(nullValue())));
		
		/*Long newBalance = userService.getCurrentBalance(balance);
		
		Assert.isTrue((addedValue + initialBalance) == newBalance, "balance must be right");*/

	}
	
	@Test
	public void transferMoney() throws Exception {

	
		Set<UserAccount> accounts = userService.getAccounts();
		
		
		if (accounts.size() == 0 || accounts == null) {

			MoneyTypes m = new MoneyTypes();
			m.setId(MoneyTypesEnum.TRY.val());

			UserAccount account = new UserAccount();
			account.setBalance(1000L);

			account.setMoneyType(m);

			User u = userService.addAcount(account);

			accounts = u.getAccounts();

		}
		
		
		UserAccount accounts1 = (UserAccount) accounts.toArray()[0];
		
		Long addedValue = 150L;
		
		BalanceDto balance = new BalanceDto();
		balance.setAccount(accounts1);
		balance.setAmount(addedValue);
		balance.setType(AccountTransactionType.ADD);
		
		Long initialBalance = accounts1.getBalance();
		
		User targetUser = userService.findByUserName("test1234");
		UserAccount targetAccount = (UserAccount) targetUser.getAccounts().toArray()[0];
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		mapper.configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH, false);
		UserWithoutPassword us = mapper.convertValue(targetUser, UserWithoutPassword.class);
		
		
		TransferDto t = new TransferDto();
		t.setTargetAccount(targetAccount);
		t.setTargetUser(us);
		t.setBalance(balance);

		
		this.mockMvc
				.perform(post("/restful/accounts/transfer").contentType(MediaType.APPLICATION_JSON).content(asJsonString(t)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data", is(true)));
		
		/*Long newBalance = userService.getCurrentBalance(balance);
		
		Assert.isTrue((addedValue + initialBalance) == newBalance, "balance must be right");*/

	}
	
	@Test
	public void loadBalance() throws Exception {

	
		Set<UserAccount> accounts = userService.getAccounts();
		
		
		if (accounts.size() == 0 || accounts == null) {

			MoneyTypes m = new MoneyTypes();
			m.setId(MoneyTypesEnum.TRY.val());

			UserAccount account = new UserAccount();

			account.setMoneyType(m);

			User u = userService.addAcount(account);

			accounts = u.getAccounts();

		}
		
		
		UserAccount accounts1 = (UserAccount) accounts.toArray()[0];
		
		Long addedValue = 150L;
		
		BalanceDto balance = new BalanceDto();
		balance.setAccount(accounts1);
		balance.setAmount(addedValue);
		balance.setType(AccountTransactionType.ADD);
		

		
		this.mockMvc
				.perform(post("/restful/accounts/loadBalance").contentType(MediaType.APPLICATION_JSON).content(asJsonString(balance)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data", is(true)));
		
		/*Long newBalance = userService.getCurrentBalance(balance);
		
		Assert.isTrue((addedValue + initialBalance) == newBalance, "balance must be right");*/

	}

	
	private void getResponseForPassword(String name, String email, String password) throws Exception {

		this.mockMvc
				.perform(post("/user/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
								new BasicNameValuePair("firstName", name), new BasicNameValuePair("email", email),
								new BasicNameValuePair("password", password),
								new BasicNameValuePair("matchingPassword", password))))))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.error", is("error")));

	}

	private void getResponseForPasswordOk(String name, String email, String password) throws Exception {

		this.mockMvc
				.perform(post("/user/registration").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
								new BasicNameValuePair("firstName", name), new BasicNameValuePair("email", email),
								new BasicNameValuePair("password", password),
								new BasicNameValuePair("matchingPassword", password))))))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.message", is("success")));

	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
