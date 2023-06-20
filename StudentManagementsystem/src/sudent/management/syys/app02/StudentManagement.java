package sudent.management.syys.app02;

import java.util.Scanner;

public class StudentManagement {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter number of Students you want to enroll : ");
			int numOfStd = sc.nextInt();
			Student[] stdarray = new Student[numOfStd];
			for (int i = 0; i < numOfStd; i++) {
				stdarray[i] = new Student();
				stdarray[i].enroll();
				stdarray[i].payTuition();
				stdarray[i].showInfo();
			}
		}

	}

}
