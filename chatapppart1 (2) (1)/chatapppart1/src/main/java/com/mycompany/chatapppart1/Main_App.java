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
public class Main_App {
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
   
// Inside your main method, after login is successful:
    //Scanner scanner = new Scanner(System.in);
    
        if (loggedIn) {
            System.out.println("Welcome to ChatApp.");

            boolean running = true;
            while (running) {
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                int choice = input.nextInt();
                input.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("How many messages would you like to send?");
                        int numMessages = input.nextInt();
                        input.nextLine();

                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1;
                            System.out.println("--- Message " + messageNumber + " ---");

                            // Get recipient
                            System.out.println("Enter recipient cell number:");
                            String recipient = input.nextLine();
                            
                            // Get message text
                            System.out.println("Enter your message:");
                            String messageText = input.nextLine();
                            
                            Message msg = new Message(messageNumber, recipient,messageText );
                            // Validate recipient
                            //msg = new Message(messageNumber, recipient, "");
                            String recipientResult = msg.checkRecipientCell(recipient);
                            System.out.println(recipientResult);

                            if (!recipientResult.equals("Cell phone number successfully captured.")) {
                                continue; // skip this message
                            }

                            // Validate length
                            String lengthResult = msg.checkMessageLength(messageText);
                            System.out.println(lengthResult);

                            if (!lengthResult.equals("Message ready to send.")) {
                                continue;
                            }

                            // Set the message text (need to recreate object or use setter)
                            //msg = new Message(messageNumber, recipient, messageText);

                            // Ask what to do
                            String action = msg.sentMessage(input);
                            System.out.println(action);

                            // Display details
                            if (action.equals("Message successfully sent.") || 
                                action.equals("Message successfully stored.")) {
                                System.out.println("Message ID: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                System.out.println("Recipient: " + msg.getRecipient());
                                System.out.println("Message: " + msg.getMessageText());
                            }


                        System.out.println("Total messages sent: " + Message.getTotalMessages());
                        }
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Exiting.");
        }
        
    
    }    
}


