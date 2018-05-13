package com.mvc.login.dto;

import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.mvc.login.entity.UserAccount;
import com.mvc.login.validation.ValidEmail;
import com.mvc.login.validation.ValidPassword;

public class UserDto {
	
	private Long id;
	
	private String username;

	

	@NotNull
    @NotEmpty
    @Length(min=3, message="username have to be more than 3 characters")
    private String firstName;
     
    private String lastName;
     
    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    private String matchingPassword;
     
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;
    
    @Nullable
    private List<UserAccount> accounts;
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    public Long getId() {
  		return id;
  	}

  	public void setId(Long id) {
  		this.id = id;
  	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<UserAccount> accounts) {
		this.accounts = accounts;
	}
     
    // standard getters and setters
}