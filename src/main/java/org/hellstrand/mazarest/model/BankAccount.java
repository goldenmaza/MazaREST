package org.hellstrand.mazarest.model;

import java.math.BigDecimal;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class BankAccount {
	private int id;
	private String number;
	private String name;
	private BigDecimal balance;
	private boolean creditCard;
	private boolean synthetic;
	
	public BankAccount() {}
	
	public BankAccount(int id, String number, String name, BigDecimal balance, boolean creditCard, boolean synthetic) {
		this.id = id;
		this.number = number;
		this.name = name;
		this.balance = balance;
		this.creditCard = creditCard;
		this.synthetic = synthetic;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setCreditCard(boolean creditCard) {
		this.creditCard = creditCard;
	}

	public boolean isCreditCard() {
		return creditCard;
	}

	public void setSynthetic(boolean synthetic) {
		this.synthetic = synthetic;
	}

	public boolean isSynthetic() {
		return synthetic;
	}
}
