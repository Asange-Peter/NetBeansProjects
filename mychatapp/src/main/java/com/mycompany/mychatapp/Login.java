package com.mycompany.mychatapp;

public class Login {
    
    // These variables show where the user details are stored
    // once the user registers, their details will appear here.
    String username;
    String password;
    String phoneNumber;

    public Login(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phone;
    }

    public Login() {
    }

    // Check if username has an underscore and length is <= 5
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Check password complexity: at least 8 characters, 1 uppercase, 1 number, 1 special character
    public boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }

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
        return phone != null && phone.startsWith("+27") && phone.length() <= 12;
    }

    // Register user method
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure it contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        return "User registered successfully.";
    }

    // Login user method
    public boolean loginUser(String username, String password) {
        return this.username != null
                && this.password != null
                && this.username.equals(username)
                && this.password.equals(password);
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

