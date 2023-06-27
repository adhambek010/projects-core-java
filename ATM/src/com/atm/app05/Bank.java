package com.atm.app05;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;

	/**
	 * Create a new Bank object with empty lists of user and accounts
	 * 
	 * @param name the name of the bank
	 */
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<>();
		this.accounts = new ArrayList<>();
	}

	/**
	 * generate a new universally unique ID for a user
	 * 
	 * @return the uuid
	 */
	public String getNewUserUUID() {
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;
		do {
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}
			nonUnique = false;
			for (User u : this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);

		return uuid;
	}

	/**
	 * Generates a new universally unique ID for an account
	 * 
	 * @return the uuid
	 */
	public String getNewAccountUUID() {
		String uuid = "";
		Random rng = new Random();
		int len = 8;
		boolean nonUnique;
		do {
			for (int i = 0; i < len; i++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}
			nonUnique = false;
			for (Account a : this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		return uuid;
	}

	/**
	 * Add an account
	 * 
	 * @param anAcct the account to add
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}

	/**
	 * Create a new user of the bank
	 * 
	 * @param fName the user's first name
	 * @param lNAme the user's last name
	 * @param pin   the user's pin
	 * @return the new User object
	 */

	public User addUser(String fName, String lNAme, String pin) {
		User newUser = new User(fName, lNAme, pin, this);
		this.users.add(newUser);
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		return newUser;
	}

	/**
	 * Get the User object associated with particular userID and pin, if they are
	 * valid
	 * 
	 * @param userID the UUID of the user to log in
	 * @param pin    the pin of the user
	 * @return the User object, if the login is successful, or null, if it is not
	 */
	public User userLogin(String userID, String pin) {
		for (User u : this.users) {
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * Get the name of the bank
	 * 
	 * @return the name of the bank
	 */
	public String getName() {
		return this.name;
	}
}
