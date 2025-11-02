package org.example;

import java.util.Scanner;

public class Main {

    public static final String PRINT_AREA = "AREA : %d";
    public static final String PRINT_PERIMETER = "PERIMETER : %d";
    public static final String LENGTH_FIELD = "LENGTH";
    public static final String WIDTH_FIELD = "WIDTH";
    private static String GRETTINGS = "AREA AND PERIMETER CALCULATION";
    private static String INPUT = "Please enter %s: ";
    private static final String ERROR_MESSAGE = "Error: Value cannot be negative. Please re-enter.";

    private static int length = 0;
    private static int width = 0;

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(GRETTINGS);

        readUserInput();

        calculateAreaAndPrint();
        calculatePerimeterAndPrint();
    }

    private static void calculateAreaAndPrint() {
        int area = width * length;

        System.out.println(String.format(PRINT_AREA, area));
    }

    private static void calculatePerimeterAndPrint() {
        int perimeter = 2 * (width + length);

        System.out.println(String.format(PRINT_PERIMETER, perimeter));
    }

    private static void readUserInput() {
        width = getPositiveInt(LENGTH_FIELD);
        length = getPositiveInt(WIDTH_FIELD);

        if (isInputNegative()) {
            System.out.println(ERROR_MESSAGE);

            readUserInput();
        }
    }

    private static int getPositiveInt(String fieldName) {
        System.out.print(String.format(INPUT, fieldName));

        return sc.nextInt();
    }

    private static boolean isInputNegative() {
        return isNegative(length) || isNegative(width);
    }

    private static boolean isNegative(int val) {
        return val < 0;
    }
}