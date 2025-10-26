package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static String CHECK_BALANCE = "Check Balance";
    private final static String DEPOSIT_FUNDS = "Deposit Funds";
    private final static String WITHDRAW_FUNDS = "Withdraw Funds";
    private final static String EXIT = "Exit";
    private final static Scanner sc = new Scanner(System.in);

    private static double balance = 0;

    private final static List<String> options = Arrays.asList(CHECK_BALANCE, DEPOSIT_FUNDS, WITHDRAW_FUNDS, EXIT);

    public static void main(String[] args) {
        boolean isExit = false;

        while(!isExit) {
            int inputOption = getInputFromUser();

            isExit = operation(inputOption);
        }

        sc.close();
        balance = 0;
    }

    private static boolean operation(int inputOption) {
        boolean isExit = false;

        switch (inputOption) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositFunds();
                break;
            case 3:
                withdrawFund();
                break;
            case 4:
                isExit = true;
                ExitOperation();
                break;
            default:
                System.out.println("Please Choose option:(1, 2, 3, 4)");
                break;
        }

        return isExit;
    }

    private static void checkBalance() {
        String currentBalance = String.format("Current Balance : %.2f", balance);

        System.out.println(currentBalance);
    }

    private static void depositFunds() {
        System.out.println("\nPlease enter value in taka to deposit");
        double balance = sc.nextDouble();

        addBalance(balance);
    }

    private static void withdrawFund() {
        System.out.println("\nPlease enter value in taka to withdraw");
        double balance = sc.nextDouble();

        removeBalance(balance);
    }

    private static void ExitOperation() {
        System.out.println("Thank you for using the ATM! Goodbye.");
    }

    private static int getInputFromUser() {
        greetings();

        return sc.nextInt();
    }

    private static void greetings() {
        System.out.println("\n\nPlease choose an option: (e.g. 1 for check balance, 2 for deposit funds");
        System.out.println("=============================");
        for (int i = 0; i < options.size(); i++) {
            String option = String.format("%d. %s", i + 1, options.get(i));

            System.out.println(option);
        }
        System.out.println("=============================\n\n");
    }

    private static void addBalance(double val) {
        if (!isPositive(val)) {
            System.out.println("Deposit money should be positive");

            return;
        }

        balance += val;
        printSuccessMessage("Deposit");
    }

    private static void removeBalance(double val) {
        if (!isPositive(val)) {
            System.out.println("Withdraw fund should be positive");

            return;
        }

        if (100 < val) {
            System.out.println("You cannot withdraw more than 100 taka");

            return;
        }

        if (balance - val < 0) {
            System.out.println("Insufficient funds");

            return;
        }

        balance -= val;
        printSuccessMessage("Withdrawn");
    }

    private static boolean isPositive(double val) {
        return 0 < val;
    }

    private static void printSuccessMessage(String s) {
        System.out.println("Successfully " + s);
    }
}