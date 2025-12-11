package org.example;

import org.example.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.example.Helper.*;

/**
 * @author aswan-kib
 * @since 12/8/25
 */
public class FinanceTrackerApp {

    private static final int MAX_ATTEMPT_FROM_USER = 3;
    private static List<Transaction> transactions = new ArrayList<>();;

    public static void main(String[] args) {
        greetings();
        boolean isExist = false;

        while (!isExist) {
            String menu = printMenuAndGet();

            switch (menu) {
                case CREATE:
                    create();
                    break;
                case READ:
                    read();
                    break;
                case UPDATE:
                    update();
                    break;
                case DELETE:
                    delete();
                    break;
                case CALCULATE_BALANCE:
                    calculateBalance();
                    break;
                case EXIT:
                    isExist = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + menu);
            }
        }

        thankYou();
    }

    private static void create() {
        System.out.println("Creating a new transaction");

        int attemptsLeft = MAX_ATTEMPT_FROM_USER;

        while (attemptsLeft > 0) {
            Transaction transaction = getTransaction();
            Transaction existingTransaction = findTransactionById(transaction.getId(), transactions);

            if (transaction.isValid() && isNull(existingTransaction)) {
                transactions.add(transaction);
                System.out.println("Transaction Added. Id: " + transaction.getId());
                
                return;
            }

            attemptsLeft--;

            if(nonNull(existingTransaction)) {
                System.out.println(String.format("Transaction with id: %s, already exist", existingTransaction.getId()));
            }

            System.out.println("Invalid input. Please try again. Attempts left: " + attemptsLeft);
        }

        System.out.println("Error occurred: Maximum attempts reached. Transaction not created.");
    }

    private static void read() {
        System.out.println("Read all transactions");

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void update() {
        System.out.println("Update transaction");
        int attemptsLeft = MAX_ATTEMPT_FROM_USER;

        while (attemptsLeft > 0) {
            String transactionId = getTransactionIdFromUser();
            Transaction transaction = findTransactionById(transactionId, transactions);

            if (isNull(transaction)) {
                attemptsLeft--;
                System.out.println("Transaction not found. Attempts left: " + attemptsLeft);

                continue;
            }

            Transaction newTransaction = getTransaction(transactionId);

            if (newTransaction.isValid()) {
                transaction.updateTransaction(newTransaction);
                System.out.println("Transaction Updated. Id: " + transactionId);

                return;
            }

            attemptsLeft--;
            System.out.println("Invalid input. Attempts left: " + attemptsLeft);
        }

        System.out.println("Error: Maximum attempts reached. Transaction not updated.");
    }


    private static void delete() {
        System.out.println("Delete a transaction");
        int attemptsLeft = MAX_ATTEMPT_FROM_USER;

        while (attemptsLeft > 0) {
            String transactionId = getTransactionIdFromUser();
            Transaction transaction = findTransactionById(transactionId, transactions);

            if (nonNull(transaction)) {
                transactions.remove(transaction);
                System.out.println("Transaction Deleted. Id: " + transactionId);

                return;
            }

            attemptsLeft--;
            System.out.println("Transaction not found. Attempts left: " + attemptsLeft);
        }

        System.out.println("Error: Maximum attempts reached. Transaction not deleted.");
    }


    private static void calculateBalance() {
        System.out.println("Calculate balance for all transactions");
        double totalAmount = 0;

        for (Transaction transaction : transactions) {
            if (transaction.isIncomeType()) {
                totalAmount += transaction.getAmount();
            } else {
                totalAmount -= transaction.getAmount();
            }
        }

        System.out.println("Total Balance: " + totalAmount);
    }
}