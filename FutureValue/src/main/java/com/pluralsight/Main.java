package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keystrokes = new Scanner(System.in);

        System.out.println("How much will you be depositing? :");
        double deposit = keystrokes.nextDouble();

        System.out.println("What is your account's interest rate :");
        double interestRate = keystrokes.nextDouble();

        System.out.println("How long will the deposit be left to accrue interest? :");
        double loanLength = keystrokes.nextDouble();

        double trueInterest = interestRate / 100;

        double interestAccrued = deposit * trueInterest * loanLength;

        double FV = deposit + interestAccrued;

        System.out.println( " If you deposit " + deposit + " in a CD that earns " + interestRate +
                " interest and matures in " + loanLength);

        System.out.println(" years, your CD's ending balance will be "
                + FV + " you would have earned " + interestAccrued +  " in interest");




    }
}