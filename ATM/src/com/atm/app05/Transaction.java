package com.atm.app05;

import java.util.Date;

@SuppressWarnings("unused")
public class Transaction {
	private double amt;
	private Date timestamp;
	private String memo;
	private Account inAccount;

	/**
	 * Create a new transaction
	 * 
	 * @param amt       the amount transacted
	 * @param inAccount the account the transaction belongs to
	 */
	public Transaction(double amt, Account inAccount) {
		this.amt = amt;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
	}

	/**
	 * Create a new transaction
	 * 
	 * @param amt       the amount transacted
	 * @param memo      the memo for the transaction
	 * @param inAccount the account the transaction belongs to
	 */
	public Transaction(double amt, String memo, Account inAccount) {
		this(amt, inAccount);
		this.memo = memo;
	}

	/**
	 * Get the amount of the transaction
	 * 
	 * @return the amount
	 */
	public double getAmount() {

		return this.amt;
	}

	/**
	 * get a String summarizing the transaction
	 * 
	 * @return the summary String
	 */
	public String getSummaryLine() {
		if (this.amt >= 0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amt, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), -this.amt, this.memo);

		}
	}

}
