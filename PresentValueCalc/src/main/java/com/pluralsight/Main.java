package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner keystrokes = new Scanner(System.in);


        System.out.println("How much would the monthly payout be :");
        double payout = keystrokes.nextDouble();

        System.out.println("What is the expected interes rate :");
        double interest = keystrokes.nextDouble();

        System.out.println("How many years will this be payed out :");
        double payoutLength = keystrokes.nextDouble();

        double monthlyRate = interest / 100 / 12;

        double totalPayments = payoutLength * 12;

        




    }
}