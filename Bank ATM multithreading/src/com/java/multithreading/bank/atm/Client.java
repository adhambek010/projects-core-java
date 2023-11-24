package com.java.multithreading.bank.atm;

import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            byte[] pin = "1234".getBytes();
            final int idgen = idGenerator();
            System.out.println("Your unique ID is " + idgen);
            System.out.print("\nEnter your name : ");
            String name = sc.nextLine();
            System.out.println("Welcome " + name + " to our ATM machine!");

            System.out.print("Enter your ID : ");
            int id = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            System.out.print("Enter your pin : ");
            String inputPin = sc.nextLine().trim();

            if (checkPin(inputPin.getBytes(), pin, id, idgen)) {
                System.out.println("You successfully logged in!");

                Account account = new Account();
                AccountHolder holder = new AccountHolder(name, idgen, account);
                System.out.println("You have " + account.getTotalBalance()+"$ in your balance");
                System.out.println("Would you like withdraw or deposit");
                System.out.println("For withdraw --> 1");
                System.out.println("For deposit  --> 2");
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        holder.getWithdrawAmt();
                        break;
                    case 2:
                        holder.deposit();
                }



            } else {
                System.out.println("Your pin and ID do not match, please try again :)");
            }
        }
    }

    public static int idGenerator() {
        int id = 0;
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            id *= 10;
            id += (rd.nextInt(10));
        }
        return id;
    }

    private static boolean checkPin(byte[] actualPin, byte[] inputPin, int id, int inputID) {
        int pin1 = 0;
        for (byte b : actualPin) {
            pin1 *= 10;
            pin1 += b;
        }
        int pin2 = 0;
        for (byte b2 : inputPin) {
            pin2 *= 10;
            pin2 += b2;
        }
        return pin2 == pin1 && id == inputID;
    }
}
