package com.mvc.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "USER_ACCOUNT")
@Where(clause="is_deleted = 0")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "balance")
	private Long balance = 0L;

	@ManyToOne
	@JoinColumn(name = "account_type")
	private MoneyTypes moneyType;

	@Column(name = "user_id")
	private Long userId;

	@Column(name="is_deleted")
	private Integer isDeleted = 0;

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public MoneyTypes getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(MoneyTypes moneyType) {
		this.moneyType = moneyType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
