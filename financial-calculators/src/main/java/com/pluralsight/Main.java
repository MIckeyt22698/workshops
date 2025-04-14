package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keystrokes = new Scanner(System.in);


        System.out.println("What is The principal:");
        double principal = keystrokes.nextDouble();


        System.out.println("What is The interest rate :");
        double interest = keystrokes.nextDouble();

        System.out.println("What is the lifespan of the loan in years:");
        double loanLength = keystrokes.nextDouble();

        double trueInterest = interest / 100;


        double monthlyPay = principal / (loanLength * 12);


        double cashInterest = principal * trueInterest * loanLength;



        System.out.println("A $" + principal + " " + "loan at "  + interest + " for "  + loanLength +
                " years would have a $" + monthlyPay + "/mo  payment with a total interest of $" + cashInterest );













    }
}