package com.atm.app05;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Account {
	private String name;
	private String uuID;
	private User holder;
	private ArrayList<Transaction> trx;

	/**
	 * Create a new Account
	 * 
	 * @param name    the name of account
	 * @param holder  the User object that holds this account
	 * @param theBank the bank that issues the account
	 */
	public Account(String name, User holder, Bank theBank) {
		this.name = name;
		this.holder = holder;
		this.uuID = theBank.getNewUserUUID();
		this.trx = new ArrayList<Transaction>();

	}

	/**
	 * get the account UUID
	 * 
	 * @return the uuid
	 */
	public String getUUID() {
		return this.uuID;
	}

	/**
	 * Get summary line for the account
	 * 
	 * @return the string summary
	 */
	public String getSummaryLine() {
		double balance = this.getBalance();
		if (balance >= 0) {
			return String.format("%s : $%.2f : %s", this.uuID, balance, this.name);
		} else {
			return String.format("%s : $(%.2f) : %s", this.uuID, balance, this.name);
		}
	}

	/**
	 * Get balance of this account by adding the amounts of the transactions
	 * 
	 * @return the balance value
	 */
	public double getBalance() {
		double balance = 0;
		for (Transaction t : this.trx) {
			balance += t.getAmount();
		}
		return balance;
	}

	/**
	 * Print the transaction history of the account
	 */
	public void printTransHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.uuID);
		for (int i = this.trx.size() - 1; i >= 0; i--) {
			System.out.println(this.trx.get(i).getSummaryLine());
		}
		System.out.println();

	}

	/**
	 * Add a new transaction in this account
	 * 
	 * @param amt  the amount transacted
	 * @param memo the transaction memo
	 */
	public void addTransaction(double amt, String memo) {
		Transaction newTrans = new Transaction(amt, memo, this);
		this.trx.add(newTrans);
	}

}
