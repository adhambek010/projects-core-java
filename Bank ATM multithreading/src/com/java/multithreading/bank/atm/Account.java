package com.java.multithreading.bank.atm;

/**
 * Created by Adkham 11.17.2023
 */
public class Account {
    private int totalBalance;

    private int lastTrx;

    public Account() {
        this.totalBalance = 10000;
    }

    public int getTotalBalance(){
        return totalBalance;
    }

    public void updateTotalBal(int bal){
        this.totalBalance += bal;
    }

    public synchronized void withdraw(int amt){
        totalBalance -= amt;
        this.lastTrx = amt;
    }

    public String getLastTrx(){
        return "-" + lastTrx + "$";
    }


}

