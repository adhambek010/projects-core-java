package sudent.management.syys.app02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {
	private String firstName;
	private String lastName;
	private int gradeYear;
	private String studentID;
	private String courses = "";
	private int tuitionBal = 0;
	private static int costCourse = 600;
	private static int id = 1000;

	private Scanner sc = new Scanner(System.in);

	public Student() {
		System.out.print("Enter student's first name: ");
		this.firstName = sc.nextLine();
		System.out.print("Enter student's last name: ");
		this.lastName = sc.nextLine();
		this.studentID = setStudentId();
		this.gradeYear = getGradeYearFromUser();
	}

	private int getGradeYearFromUser() {
		int grade;
		while (true) {
			try {
				System.out.print("1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior\nEnter student class level: ");
				grade = sc.nextInt();
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input for student class level. Please try again.");
				sc.nextLine();
			}
		}
		return grade;
	}

	private String setStudentId() {
		id++;
		return gradeYear + "" + id;
	}

	public void enroll() {
		while (true) {
			System.out.print("Enter course to enroll (Q to quit): ");
			String course = sc.nextLine();
			if (!course.equalsIgnoreCase("Q")) {
				courses += "\n" + course;
				tuitionBal += costCourse;
			} else {
				break;
			}
		}
	}

	public void viewBalance() {
		System.out.println("Your balance is: $" + tuitionBal);
	}

	public void payTuition() {
		viewBalance();
		System.out.print("Enter your payment: $");
		int payment = sc.nextInt();
		sc.nextLine(); 
		tuitionBal -= payment;
		System.out.println("Thank you for your payment: $" + payment);
		viewBalance();
	}

	public void showInfo() {
		System.out.println("Name: " + firstName + " " + lastName + "\nGrade Level: " + gradeYear + "\nStudent ID: "
				+ studentID + "\nCourses enrolled: " + courses + "\nBalance: $" + tuitionBal+"\n");
	}
}
