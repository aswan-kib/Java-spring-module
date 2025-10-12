package org.example;

import java.util.Scanner;

public class Main {

    private static final String message = "Reason for Learning Java:\n" +
            "I’m learning Java because it’s a powerful, versatile, and industry-standard language widely used in backend development, enterprise systems, and Android applications. Its strong object-oriented principles and vast ecosystem make it ideal for building scalable and reliable solutions.\n" +
            "\n" +
            "Career Goal:\n" +
            "My goal is to become a skilled Software Engineer specializing in Java-based web and enterprise applications, contributing to innovative projects and advancing into a full-stack or system architecture role in the future.";

    public static void main(String[] args) {
        String name = getName();

        String formattedMessage = String.format("\n%s\n\n%s\n", name, message);

        System.out.println(formattedMessage);
    }

    private static String getName() {
        Scanner scanner =  new Scanner(System.in);

        System.out.println("Enter your name:");

        return "Akib222";
    }
}
