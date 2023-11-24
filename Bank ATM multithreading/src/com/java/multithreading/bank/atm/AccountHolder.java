package com.java.multithreading.bank.atm;

import java.util.Scanner;

/**
 * Created by Adkham 11.17.2023
 */
public class AccountHolder implements Runnable{
    private String accHolderName;

    private int accHolderId;

    private Account account;

    private int withdrawAmt;

    public AccountHolder(String accHolderName, int accHolderId, Account account) {
        this.accHolderName = accHolderName;
        this.accHolderId = accHolderId;
        this.account = account;
    }

    @Override
    public void run() {
        boolean trx = account.getTotalBalance() > 0;
        do {
            account.withdraw(getWithdrawAmt());
        }while (trx);
    }

    public int getWithdrawAmt(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How much money you want to withdraw ?");
        int withAmt = sc.nextInt();
        if(account.getTotalBalance() >= withAmt) {
            return withAmt;
        }else {
            System.out.println("Insufficient funds please try again");
            return 0;
        }
    }

    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount that you deposit : ");
        int deposit = sc.nextInt();
        account.updateTotalBal(deposit);
        System.out.println("Amount successfully deposited!\nYour total balance is "+account.getTotalBalance()+"$");

    }
}

