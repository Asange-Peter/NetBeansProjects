package com.mycompany.chatapppart1;

import java.util.Scanner;

public class Login {
    
    // These variables show where the user details are storeds
    // once the user registers, their details will appear here.
    String username;
    String password;
    String phoneNumber;

    // Check if username has an underscore and length is <= 5
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Check password complexity: at least 8 characters, 1 uppercase, 1 number, 1 special character
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    // Check if the phone number starts with "+27" and is <= 12 characters
    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() <= 12;
    }

    // Register user method with retry functionality
    public String registerUser(String username, String password, String phoneNumber) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!checkUserName(username)) {
                System.out.println("Username is not correctly formatted; please ensure it contains an underscore and is no more than five characters in length.");
                System.out.print("Enter username: ");
                username = scanner.nextLine(); // Prompt user for new username
                continue; // Restart the loop to check again
            }
            
            if (!checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted; please ensure the password contains at least eight characters, a capital letter, a number, and a special character.");
                System.out.print("Enter password: ");
                password = scanner.nextLine(); // Prompt user for new password
                continue; // Restart the loop to check again
            }
            
            if (!checkCellPhoneNumber(phoneNumber)) {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                System.out.print("Enter phone number: ");
                phoneNumber = scanner.nextLine(); // Prompt user for new phone number
                continue; // Restart the loop to check again
            }
            
            // If all checks pass
            this.username = username;
            this.password = password;
            this.phoneNumber = phoneNumber;
            return "User registered successfully.";
        }
    }

    // Login user method with retry functionality
    public boolean loginUser(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        final int MAX_ATTEMPTS = 3; // Maximum allowed attempts

        while (attempts < MAX_ATTEMPTS) {
            if (this.username.equals(username) && this.password.equals(password)) {
                return true; // Successful login
            } else {
                attempts++;
                System.out.println("Incorrect username or password. Attempt " + attempts + " of " + MAX_ATTEMPTS);
                if (attempts < MAX_ATTEMPTS) {
                    System.out.print("Please enter your username: ");
                    username = scanner.nextLine();
                    System.out.print("Please enter your password: ");
                    password = scanner.nextLine();
                }
            }
        }

        return false; // Login failed after max attempts
    }

    // Return login status message
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + username + ", it is great to see you again!";
        } else {
            return "Username or password incorrect. Please try again.";
        }
    }
}
        

                   
            
           
            
        
        
       
    
    
            
       
 
    

