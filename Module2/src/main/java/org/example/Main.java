package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String trackingCode = getTrackingCode();

        String trackingPattern = "^ID-(\\d{5})-WEIGHT:(\\d+(\\.\\d+)?)$";

        boolean isValid = Pattern.matches(trackingPattern, trackingCode);

        if (!isValid) {
            System.out.println("Invalid Tracking code");

            return;
        }

        findAndPrintTrackingCode(trackingCode, trackingPattern);
    }

    private static void findAndPrintTrackingCode(String trackingCode, String trackingPattern) {
        Pattern pattern = Pattern.compile(trackingPattern);
        Matcher matcher = pattern.matcher(trackingCode);

        if (matcher.matches()) {
            String id = matcher.group(1);
            int weight = (int) Double.parseDouble(matcher.group(2));

            System.out.println("Next tracking id: " + id);
            System.out.println("Weight without decimal point: " + weight);
        } else {
            System.out.println("Invalid tracking code");
        }
    }


    private static String getTrackingCode() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the tracking code:");

        return sc.nextLine();
    }
}