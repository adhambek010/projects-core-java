package project.email.app01;

import java.util.Scanner;

public class Email {
	private String firstName;
	private String lastName;
	private String email;
	private char[] password;
	private String department;
	private int passwordLength;
	private String companySuffix;
	Scanner sc = new Scanner(System.in);

	public Email(String firstName, String lastName, int passwordLength, String companySuffix) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.passwordLength = passwordLength;
		this.companySuffix = companySuffix;
		this.department = setDepartment();
		this.password = setPassword(passwordLength);
		this.email = this.firstName.toLowerCase() + this.lastName.toLowerCase() + "@" + this.department 
				+ this.companySuffix + ".com";
	}

	protected String setDepartment() {
		System.out.print(
				"\nCodes for department\n--------------------\nFor Sales : 1\nFor Development : 2\nFor Accounting : 3\nEnter the department code --> ");

		int depChoise = sc.nextInt();
		switch (depChoise) {
		case 1:
			return "sales";
		case 2:
			return "dev";
		case 3:
			return "acct";

		default:
			return "none";
		}
	}

	private char[] setPassword(int length) {
		String passwordSet = "ABCDEFGHIJKLMNPQRSTUVWXYZ)0123456789_-#";
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * passwordSet.length());
			password[i] = passwordSet.charAt(rand);
		}
		return password;
	}

	public void printAllData() {

		System.out.println("\nEmail creation is Succes!");
		System.out.println("--------------------------");
		System.out.println("Your first name is   : " + this.firstName);
		System.out.println("Your last name is   : " + this.lastName);
		System.out.println("Your department is   : " + this.department);
		System.out.println("Your generated email is   : " + this.email);
		System.out.print("Your generated password   : ");
		for(char c : this.password) {
			System.out.print(c);
		}
		System.out.println();
		System.out.println("Never share this password");
		System.out.println(
				"\nDo you yant change your password\nChange password : 1\nRegenerate password : 2\nNone of them : 0 --> ");
		int choise = sc.nextInt();
		if (choise == 1) {
			changePassword();
		} else if (choise == 2) {
			regeneratePassword();
		} else {
			System.out.println("Thank you visit again!!!");
		}

	}

	public void changePassword() {
		char[] password = new char[passwordLength];
		System.out.println();
		for (int i = 0; i < passwordLength; i++) {
			System.out.print("Enter your password's " + (i + 1) + " character : ");
			String s = sc.next();
			password[i] = s.charAt(0);
		}
		this.password = password;
		System.out.print("Your new password is : ");
		for(char c : password) {
			System.out.print(c);
		}
		System.out.println();
		System.out.println("Thank you visit again!!!");
	}

	public void regeneratePassword() {
		this.password = setPassword(passwordLength);
		System.out.print("Your new password is : ");
		for(char c : this.password) {
			System.out.print(c);
		}
		System.out.println();
		System.out.println("Thank you visit again!!!");
	}

}
