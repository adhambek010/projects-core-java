package banking.application.app04;

import java.util.Scanner;

public class BankinApplication {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter your first name : ");
			String fName = sc.nextLine();
			System.out.print("Enter your second name : ");
			String sName = sc.nextLine();
			System.out.println("Enter your user ID : ");
			String eID = sc.nextLine();

			BankAccount acc = new BankAccount(fName, sName, eID);
			acc.showMenu();
		}
		
	}

}
