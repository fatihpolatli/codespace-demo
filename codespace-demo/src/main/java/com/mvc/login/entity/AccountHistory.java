package com.mvc.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT_HISTORY")
public class AccountHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne
	@JoinColumn(name="account_id")
	private UserAccount account;
	
	@Column(name="action_date")
	private Long actionDate;
	
	private Long amount;
	
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	

}
