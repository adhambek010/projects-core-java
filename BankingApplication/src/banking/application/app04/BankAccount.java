package banking.application.app04;

import java.util.Scanner;

public class BankAccount {

	private int balance;
	private int prevTranasction;
	private String custFirstName;
	private String custSecondName;
	private String custID;

	public BankAccount(String custFirstName, String custSecondName, String custID) {
		this.custFirstName = custFirstName;
		this.custSecondName = custSecondName;
		this.custID = custID;
	}

	public void deposit(int amt) {
		if (amt != 0) {
			balance += amt;
			prevTranasction = amt;
		}
	}

	public void withdraw(int amt) {
		if (amt != 0) {
			balance -= amt;
			prevTranasction = -amt;
		}
	}

	public void getPrivousTrx() {
		if (prevTranasction > 0) {
			System.out.println("Deposited : " + prevTranasction);
		} else if (prevTranasction < 0) {
			System.out.println("Wthdrawn : " + Math.abs(prevTranasction));
		} else {
			System.out.println("No transaction occured");
		}
	}

	public void showMenu() {
		int option;

		System.out.println("Welcome " + custFirstName + " " + custSecondName);
		System.out.println("Your ID is " + custID + "\n\n");
		do {
			System.out.println("1. Check Balance : ");
			System.out.println("2. Deposit  : ");
			System.out.println("3. Withdraw : ");
			System.out.println("4. Previous transaction :");
			System.out.println("0. Exit :");
			System.out.println("===========================");
			System.out.println("Enter your option");
			System.out.println("===========================");
			option = input();
			switch (option) {
			case 0:
				System.out.println("---------------------------");
				System.out.println("***************************");
				System.out.println("---------------------------");
				break;
			case 1:
				System.out.println("---------------------------");
				System.out.println("Balance = " + balance);
				System.out.println("---------------------------\n");
				break;
			case 2:
				System.out.println("---------------------------");
				System.out.println("Enter an amount to deposit :");
				System.out.println("---------------------------\n\n");
				int amt = input();
				deposit(amt);
				break;
			case 3:
				System.out.println("---------------------------");
				System.out.println("Enter the amount to withdraw :");
				System.out.println("---------------------------\n\n");
				int amt2 = input();
				if (amt2 < balance) {
					withdraw(amt2);
					break;
				} else {
					try {
						throw new IllegalArgumentException(
								"Insufficient balance. You cannot enter an amount greater than your available balance.");
					} catch (IllegalArgumentException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				}

			case 4:
				System.out.println("---------------------------");
				getPrivousTrx();
				System.out.println("---------------------------\n\n");
				break;
			default:
				System.out.println("Invalid Option!. Please enter again");
				break;
			}
		} while (option != 0);
		System.out.println("-----------------------------------");
		System.out.println("Thank you for using our services!!!\nCome back soon!!!");
	}

	private int input() {
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.print("Your choise here : ");
			int option = sc.nextInt();
			return option;
		} catch (Exception e) {
			System.out.println("Invalid Option!., Please ry again");
			return input();
		}

	}
}