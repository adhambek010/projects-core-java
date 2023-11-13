package com.atm.app05;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Bank theBank = new Bank("Bank Pekao");

		User aUser = theBank.addUser("Adkam", "Salaydinov", "1234");

		Account newAccount = new Account("Checkin", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);

		User theUser;
		while (true) {
			theUser = ATM.mainMenuPrompt(theBank, sc);
			ATM.printUserMenu(theUser, sc);
		}
	}

	/**
	 * Print the Atm's login menu
	 * 
	 * @param theBank the Bank object whose accounts to use
	 * @param sc      the Scanner object to use for user input
	 * @return the authenticated User object
	 */
	public static User mainMenuPrompt(Bank theBank, Scanner sc) {
		String userID;
		String pin;
		User authUser;

		do {
			System.out.printf("\n\nWelcome to %s \n\n", theBank.getName());
			System.out.print("Enter user ID : ");
			userID = sc.nextLine();
			System.out.print("Enter your pin : ");
			pin = sc.nextLine();
			authUser = theBank.userLogin(userID, pin);
			if (authUser == null) {
				System.out.println("Incorrect user ID/pin combination. Please try again.");
			}
		} while (authUser == null);

		return authUser;
	}

	public static void printUserMenu(User theUser, Scanner sc) {
		theUser.printAccountsSummary();
		int choice;
		do {
			System.out.printf("Welcome to %s, what would you llike to do?\n", theUser.getFirstName());
			System.out.println("  1) Show transaction history");
			System.out.println("  2) Withdraw");
			System.out.println("  3) Deposit");
			System.out.println("  4) Transfer");
			System.out.println("  5) Quit\n");
			System.out.print("Enter your choisee : ");
			choice = (int) input();

			if (choice < 1 || choice > 5) {
				System.out.println("Invalid coise. Please coose 1-5");
			}
		} while (choice < 1 || choice > 5);

		switch (choice) {
		case 1:
			ATM.showTransactionHistory(theUser, sc);
			break;
		case 2:
			ATM.withdrawFunds(theUser, sc);
			break;
		case 3:
			ATM.depositFunds(theUser, sc);
			break;
		case 4:
			ATM.transferFunds(theUser, sc);
			break;
		case 5: 
			sc.nextLine();
			break;
		}
		if (choice != 5) {
			ATM.printUserMenu(theUser, sc);
		}
	}

	/**
	 * Show the transaction history for an account
	 * 
	 * @param theUser the logged-in User object
	 * @param sc      the scanner object used for user input
	 */
	public static void showTransactionHistory(User theUser, Scanner sc) {
		int theAcct;
		do {
			System.out.printf("Enter the number (1 - %d) of the account\n Whose transactions you want to see : ",
					theUser.numAccounts());
			theAcct = sc.nextInt() - 1;
			if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Invalid accoun. Please try again.");
			}
		} while (theAcct < 0 || theAcct >= theUser.numAccounts());
		theUser.printAcctTransHistory(theAcct);
	}

	/**
	 * Process transferring funds from one account to another
	 * 
	 * @param theUser the logged-in User object
	 * @param sc      the scanner object used for user input
	 */
	public static void transferFunds(User theUser, Scanner sc) {
		int fromAcct;
		int toAcct;
		double amt;
		double acctBal;

		do {
			System.out.printf("Enter the number (1-%d) of the account\nTo transfer from : ",theUser.numAccounts());
			fromAcct = sc.nextInt() - 1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid accoun. Please try again.");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBal(fromAcct);

		do {
			System.out.printf("Enter the number (1-%d) of the account\nTransfer to : ",theUser.numAccounts());
			toAcct = sc.nextInt() - 1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid accoun. Please try again.");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());

		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $\n", acctBal);
			amt = input();
			if (amt < 0) {
				System.out.println("Amount must be greater than 0");
			} else if (amt > acctBal) {
				System.out.printf("Amount must not be greater than balance of $%.02f.\n", acctBal);
			}
		} while (amt < 0 || amt > acctBal);

		theUser.addAcctTransaction(fromAcct, -1 * amt,
				String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));

		theUser.addAcctTransaction(toAcct, amt,
				String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));
	}

	/**
	 * Process a fund withdraw from an account
	 * 
	 * @param theUser the logged-in User object
	 * @param sc      the Scanner object user for user input
	 */
	public static void withdrawFunds(User theUser, Scanner sc) {
		int fromAcct;
		double amt;
		double acctBal;
		String memo;

		do {
			System.out.printf("Enter the number (1-%d) of the account to withdraw from : ",theUser.numAccounts());
			fromAcct = sc.nextInt() - 1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid accoun. Please try again.");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBal(fromAcct);

		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f): $\n", acctBal);
			amt = input();
			if (amt < 0) {
				System.out.println("Amount must be greater than 0");
			} else if (amt > acctBal) {
				System.out.printf("Amount must not be greater than balance of $%.02f.\n", acctBal);
			}
		} while (amt < 0 || amt > acctBal);
		sc.nextLine();
		System.out.print("Enter a memo : ");
		memo = sc.nextLine();

		theUser.addAcctTransaction(fromAcct, -1 * amt, memo);
	}

	/**
	 * Process a fund deposit to an account
	 * 
	 * @param the User the logged-in User object
	 * @param sc  the Scanner object used for user input
	 */
	public static void depositFunds(User theUser, Scanner sc) {
		int toAcct;
		double amt;
		double acctBal;
		String memo;

		do {
			System.out.printf("Enter the number (1-%d) of the account to deposit in : ", theUser.numAccounts());
			toAcct = sc.nextInt() - 1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid accoun. Please try again.");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBal(toAcct);

		do {
			System.out.printf("Enter the amount to Deposit (max $%.02f): $\n", acctBal);
			amt = input();
			if (amt < 0) {
				System.out.println("Amount must be greater than 0");
			} 
		} while (amt < 0);
		sc.nextLine();
		System.out.print("Enter a memo : ");
		memo = sc.nextLine();

		theUser.addAcctTransaction(toAcct, 1 * amt, memo);
	}

	private static double input() {
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.print("\nYour choise here : ");
			double option = sc.nextDouble();
			return option;
		} catch (Exception e) {
			System.out.println("Invalid Option!. Please ry again");
			return input();
		}
	}
}