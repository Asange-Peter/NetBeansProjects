/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Scanner;


/**
 *
 * @author Student
 */
public class MainApp {
    public static void main(String[]args){
        
        // Scanner enables the user to insert information
        Scanner input = new Scanner(System.in);
        
        // create an object of the login class 
        Login login = new Login();
        
        //--- REGISTRATION SECTION ---
        System.out.println("=== USER REGISTRATION ===");
        
        System.out.println("Enter a username:");
        String username = input.nextLine();
        
        System.out.print("Enter a password:");
        String password = input.nextLine();
        
        System.out.print("Enter your South African phone number (+27...");
        String phone = input.nextLine();
        
        // this code registerUser method and store the message it returns
        String response = login.registerUser(username, password, phone);
        
        // this code the registration message
        System.out.println(response);
        
        // ---LOGIN SECTION ---
        System.out.println("\n=== USER LOGIN ===");
        
        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();
        
        System.out.print("Enter your password:");
        String loginPassword = input.nextLine();
        
        // this code loginUser to check if details match teh stored ones
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        
        // Print out the correct login message
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
    }
}
        
        
        
        
        
        
    
    

