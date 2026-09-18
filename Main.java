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

        // ===== Login =====
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String loginUsername = Scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = Scanner.nextLine();

        System.out.println(loginSystem.returnLoginStatus(loginUsername, loginPassword));

        Scanner.close();
    }
}

