package banking.application.app04;

import java.util.Scanner;

public class BankAccount {

	private int balance;
	private int prevTranasction;
	private String custFName;
	private String custSName;
	private final int custID;

	public BankAccount(String custFirstName, String custSecondName, int custID) {
		this.custFName = custFirstName;
		this.custSName = custSecondName;
		this.custID = custID;
	}

	private void deposit(int amt) {
		if (amt != 0) {
			balance += amt;
			prevTranasction = amt;
		}
		System.out.printf("\nYou deposited %s$ \n\n", amt);
	}

	private void withdraw(int amt) {
		if (amt != 0) {
			balance -= amt;
			prevTranasction = -amt;
		}
		System.out.printf("\nYou eithdrawn %s$ \n\n", amt);
	}

	private void getPrivousTrx() {
		if (prevTranasction > 0) {
			System.out.printf("Deposited : %s$\n", prevTranasction);
		} else if (prevTranasction < 0) {
			System.out.printf("Wthdrawn : %s$", Math.abs(prevTranasction));
		} else {
			System.out.println("No transaction occured");
		}
	}

	protected void showMenu() {
		int option;

		System.out.printf("\nWelcome %s %s\t", custFName, custSName);
		System.out.printf("\nYour ID is %s \n\n", custID);
		do {
			System.out.println("1. Check Balance : ");
			System.out.println("2. Deposit  : ");
			System.out.println("3. Withdraw : ");
			System.out.println("4. Previous transaction :");
			System.out.println("0. Exit :");
			System.out.println("==========================");
			System.out.println("Enter your option");
			System.out.println("==========================");
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
				if (amt2 <= balance) {
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

	@SuppressWarnings("resource")
	private static int input() {
		try {
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