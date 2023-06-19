package project.email.app01;

import java.io.IOException;
import java.util.Scanner;

public class EmailApp {

	public static void main(String[] args) throws Exception {

		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter employee first name : ");
			String empfirstName = sc.next();
			System.out.print("Enter employee last name : ");
			String empLastName = sc.next();
			System.out.print("Enter your company suffix : ");
			String companySuffix = sc.next();
			int length = passwordLength();
			try {
				if (length < 8 || length > 16) {
					throw new IOException("Password range should be between 8 and 16");
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println("Please try again from the begining");
				main(null);
			}

			Email email = new Email(empfirstName, empLastName, length, companySuffix);
			email.printAllData();
		}

	}

	private static int passwordLength() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter length of your password : ");
		int length = sc.nextInt();
		try {
			if (length < 8 || length > 16) {
				throw new IOException("Password range should be between 8 and 16");
			}
			return length;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Please try again from the begining");
			return passwordLength();
		}
	}
}
