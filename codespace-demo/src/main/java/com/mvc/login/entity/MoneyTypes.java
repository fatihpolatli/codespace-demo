package com.mvc.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MONEY_TYPES")
public class MoneyTypes {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="money_label", nullable=false)
	private String moneyLabel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMoneyLabel() {
		return moneyLabel;
	}

	public void setMoneyLabel(String moneyLabel) {
		this.moneyLabel = moneyLabel;
	}
	

}
