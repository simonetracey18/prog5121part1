package com.mycompany.part1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner Scanner = new Scanner(System.in);
        Login loginSystem = new Login();

       System.out.println("=== Registration ===");

        // ===== Username with loop until valid =====
        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = Scanner.nextLine();

            System.out.println(loginSystem.getUsernameCaptureMessage(username));

            if (loginSystem.checkUserName(username)) {
                break;          // valid → exit loop
            }
            System.out.println("Please try again.\n");
        }

        // ===== Password =====
        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = Scanner.nextLine();

            System.out.println(loginSystem.getPasswordCaptureMessage(password));

            if (loginSystem.checkPasswordComplexity(password)) {
                break;
            }
            System.out.println("Please try again.\n");
        }

        // ===== Cell Phone =====
        String cellPhone;
        while (true) {
            System.out.print("Enter South African cell phone number (with +27): ");
            cellPhone = Scanner.nextLine();

            System.out.println(loginSystem.getCellPhoneCaptureMessage(cellPhone));

            if (loginSystem.checkCellPhoneNumber(cellPhone)) {
                break;
            }
            System.out.println("Please try again.\n");
        }

        // ===== First & Last name =====
        System.out.print("Enter first name: ");
        String firstName = Scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = Scanner.nextLine();

        // Register
        String registrationResult = loginSystem.registerUser(username, password, cellPhone, firstName, lastName);
        System.out.println("\n" + registrationResult);

        // ===== Login (repeat until correct) =====
        System.out.println("\n=== Login ===");
        while (true) {
            System.out.print("Enter username: ");
            String loginUsername = Scanner.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = Scanner.nextLine();

            System.out.println(loginSystem.returnLoginStatus(loginUsername, loginPassword));
            if (loginSystem.loginUser(loginUsername, loginPassword)) {
                break;
            }
        }

        // ===== Part 2: QuickChat =====
        System.out.println("\nWelcome to QuickChat.");
        int maxMessages = readNumber(Scanner, "How many messages would you like to send? ");
        int messagesEntered = 0;

        int option = 0;
        while (option != 3) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            option = readNumber(Scanner, "Option: ");

            switch (option) {
                case 1:
                    if (messagesEntered >= maxMessages) {
                        System.out.println("You have already entered all your messages.");
                        break;
                    }
                    while (messagesEntered < maxMessages) {
                        System.out.println("\n--- Message " + (messagesEntered + 1) + " ---");
                        enterMessage(Scanner, messagesEntered);
                        messagesEntered++;
                    }
                    System.out.println("\nTotal number of messages sent: " + Message.returnTotalMessages());
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please choose 1, 2 or 3.");
            }
        }

        Scanner.close();
    }

    private static void enterMessage(Scanner Scanner, int messageNumber) {
        String recipient;
        while (true) {
            System.out.print("Enter recipient cell number (with +27): ");
            recipient = Scanner.nextLine();
            Message check = new Message(messageNumber, recipient, "x");
            System.out.println(check.getRecipientCellMessage());
            if (check.checkRecipientCell() == 1) {
                break;
            }
        }

        String text;
        while (true) {
            System.out.print("Enter your message: ");
            text = Scanner.nextLine();
            if (text.trim().isEmpty()) {
                System.out.println("Message cannot be empty.");
            } else if (text.length() > 250) {
                System.out.println("Please enter a message of less than 250 characters.");
            } else {
                break;
            }
        }

        Message message = new Message(messageNumber, recipient, text);
        System.out.println(message.getMessageIDCreatedMessage());

        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            choice = readNumber(Scanner, "Choice: ");
        }

        System.out.println(message.sentMessage(choice));

        if (choice == 1) {
            System.out.println("\n" + message.printMessage());
        } else if (choice == 2) {
            while (!Scanner.nextLine().trim().equals("0")) {
                System.out.println("Press 0 to delete the message.");
            }
            System.out.println("Message deleted.");
        }
    }

    private static int readNumber(Scanner Scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(Scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
