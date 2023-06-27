package com.atm.app05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class User {
	private String fName;
	private String lName;
	private String uuID;
	private byte[] pinHash;
	private ArrayList<Account> accounts;

	/**
	 * 
	 * @param fName   the user's first name
	 * @param lName   the users last name
	 * @param pin     the users account pin number
	 * @param theBank the Bank object that the user is a customer of
	 */
	public User(String fName, String lName, String pin, Bank theBank) {
		this.fName = fName;
		this.lName = lName;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
			System.exit(1);
		}
		this.uuID = theBank.getNewUserUUID();
		this.accounts = new ArrayList<Account>();
		System.out.printf("New user %s, %s with ID %s created.", fName, fName, this.uuID);
	}

	/**
	 * Add an account for the user
	 * 
	 * @param anAcct the account to add
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}

	public String getUUID() {

		return this.uuID;
	}

	/**
	 * Check whether a given pin matches the true User pin
	 * 
	 * @param aPin the pin to
	 * @return whether the pin is valid or not
	 */
	public boolean validatePin(String aPin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
			System.exit(1);
		}
		return false;
	}

	/**
	 * Return the users first name
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return this.fName;
	}

	/**
	 * Print summaries of the account
	 */
	public void printAccountsSummary() {
		System.out.printf("\n\n%s's accounts summary\n", this.fName);
		for (int i = 0; i < this.accounts.size(); i++) {
			System.out.printf("  %d %s\n", i + 1, this.accounts.get(i).getSummaryLine());
		}
		System.out.println();
	}

	/**
	 * Get number of accounts of the user
	 * 
	 * @return the number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}

	/**
	 * Print transaction history for a particular account.
	 * 
	 * @param acctInd the index of the account to use
	 */
	public void printAcctTransHistory(int acctInd) {
		this.accounts.get(acctInd).printTransHistory();

	}

	/**
	 * Get balance of particular account
	 * 
	 * @param acctIdx index of account to use
	 * @return the balance of account
	 */
	public double getAcctBal(int acctIdx) {

		return this.accounts.get(acctIdx).getBalance();
	}

	/**
	 * Get the UUID of a particular account
	 * 
	 * @param acctIdx the index of the account to use
	 * @return the UUID of the account
	 */
	public String getAcctUUID(int acctIdx) {

		return this.accounts.get(acctIdx).getUUID();
	}

	public void addAcctTransaction(int acctIdx, double amt, String memo) {
		this.accounts.get(acctIdx).addTransaction(amt, memo);

	}

}
