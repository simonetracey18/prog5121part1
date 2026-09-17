/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
package com.mycompany.part1;
public class Login {
  private String storedUsername;
    private String storedPassword;
    private String storedCellPhoneNumber;
    private String firstName;
    private String lastName;

    // ===== Username =====
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
      return username.contains("_") && username.length() <= 5;
    }

    public String getUsernameCaptureMessage(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    // ===== Password =====
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

      return hasCapital && hasNumber && hasSpecial;
    }

    public String getPasswordCaptureMessage(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    // ===== Cell Phone =====
    public boolean checkCellPhoneNumber(String CellPhoneNumber) {
        if (CellPhoneNumber == null) {
            return false;
        }
        // Must start with +27 and be a reasonable length (common for this assignment)
        if (CellPhoneNumber.startsWith("+27") && CellPhoneNumber.length() <= 12) {
            // Check that the rest are digits
            String numberPart = CellPhoneNumber.substring(3);
            for (int i = 0; i < numberPart.length(); i++) {
                if (!Character.isDigit(numberPart.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public String getCellPhoneCaptureMessage(String cellPhoneNumber) {
        if (checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
    }

    // ===== Registration =====
    public String registerUser(String username, String password, String CellPhoneNumber,
                               String firstName, String lastName) {

        if (!checkUserName(username)) {
            return getUsernameCaptureMessage(username);
        }
        if (!checkPasswordComplexity(password)) {
            return getPasswordCaptureMessage(password);
        }
        if (!checkCellPhoneNumber(CellPhoneNumber)) {
            return getCellPhoneCaptureMessage(CellPhoneNumber);
        }

        // All good – store the details
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhoneNumber = CellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User has been registered successfully.";
    }

    // ===== Login =====
    public boolean loginUser(String Username, String Password) {
        if ((storedUsername == null || storedPassword == null) || null == storedCellPhoneNumber ) {
            return false;
        } else {
        }
      return Username.equals(storedUsername) && Password.equals(storedPassword);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
    

