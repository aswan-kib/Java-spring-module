package org.example;

import org.example.entity.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;

/**
 * @author aswan-kib
 * @since 12/9/25
 */
public class Helper {
    public static final String THANK_YOU_FOR_USING_FINANCE_TRACKER_CONSOLE_APP = "Thank you for using Finance Tracker Console App";
    private static Map<Integer, String> MENU_MAP = new HashMap<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String WELCOME_MESSAGE = "Welcome to Finance Tracker Console App";
    public static final String CREATE = "Create";
    public static final String READ = "Read";
    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final String CALCULATE_BALANCE = "Calculate Balance";
    public static final String EXIT = "Exit";

    private static final List<String> PROPMT = Arrays.asList(CREATE, READ, UPDATE, DELETE, CALCULATE_BALANCE, EXIT);

    static {
        AtomicInteger counter = new AtomicInteger(1);

        for (String item : PROPMT) {
            MENU_MAP.put(counter.getAndIncrement(), item);
        }

        MENU_MAP = Collections.unmodifiableMap(MENU_MAP);
    }

    public static void greetings() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void thankYou() {
        System.out.println(THANK_YOU_FOR_USING_FINANCE_TRACKER_CONSOLE_APP);
    }

    private static void  printMenu() {
        for (Map.Entry<Integer, String> entry : MENU_MAP.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        System.out.println("Please choose a option between (1 to 6)");
    }

    private static String getMenu(Integer menuNo) {
        String menu = MENU_MAP.get(menuNo);

        if (isNull(menu)) {
            throw new IllegalArgumentException("Menu number " + menuNo + " is invalid");
        }

        return menu;
    }

    public static String printMenuAndGet() {
        printMenu();

        int menu = getIntFromUser();

        return getMenu(menu);
    }

    public static Double getDoubleFromUser(String filed) {
        String input = getStringFromUser(filed);

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static int getIntFromUser() {
        String input = getStringFromUser();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid integer: " + input);
        }
    }

    public static String getStringFromUser(String filed) {
        System.out.print("Please enter transaction " + filed + ": ");

        return getStringFromUser();
    }

    public static String getStringFromUser() {
        return SCANNER.nextLine().trim();
    }

    public static String getTransactionIdFromUser() {
        return getStringFromUser("Id");
    }

    public static LocalDate getDateFromString(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isEmpty(String s) {
        return isNull(s) || s.trim().isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static Transaction findTransactionById(String id, List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }

        return null;
    }

    public static Transaction getTransaction() {
        String id = getTransactionIdFromUser();

        return getTransaction(id);
    }

    public static Transaction getTransaction(String id) {
        String type = getStringFromUser("Type");
        Double amount = getDoubleFromUser("Amount");
        String description = getStringFromUser("Description");
        LocalDate date = getDateFromString(getStringFromUser("Date"));

        return new Transaction(id, type, amount, description, date);
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");

        return date.format(fmt);
    }
}
