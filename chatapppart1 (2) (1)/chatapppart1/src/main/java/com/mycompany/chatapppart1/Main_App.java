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
    Scanner scanner = new Scanner(System.in);
    Message msg = new Message();
        if (loggedIn) {
            System.out.println("Welcome to ChatApp.");

            boolean running = true;
            while (running) {
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("How many messages would you like to send?");
                        int numMessages = scanner.nextInt();
                        scanner.nextLine();

                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1;
                            System.out.println("--- Message " + messageNumber + " ---");

                            // Get recipient
                            System.out.println("Enter recipient cell number:");
                            String recipient = scanner.nextLine();

                            // Validate recipient
                            msg = new Message(messageNumber, recipient, "");
                            String recipientResult = msg.checkRecipientCell(recipient);
                            System.out.println(recipientResult);

                            if (!recipientResult.equals("Cell phone number successfully captured.")) {
                                continue; // skip this message
                            }

                            // Get message text
                            System.out.println("Enter your message:");
                            String messageText = scanner.nextLine();

                            // Validate length
                            String lengthResult = msg.checkMessageLength(messageText);
                            System.out.println(lengthResult);

                            if (!lengthResult.equals("Message ready to send.")) {
                                continue;
                            }

                            // Set the message text (need to recreate object or use setter)
                            msg = new Message(messageNumber, recipient, messageText);

                            // Ask what to do
                            String action = msg.sentMessage(scanner);
                            System.out.println(action);

                            // Display details
                            if (action.equals("Message successfully sent.") || 
                                action.equals("Message successfully stored.")) {
                                System.out.println("Message ID: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                System.out.println("Recipient: " + msg.getRecipient());
                                System.out.println("Message: " + msg.getMessageText());
                            }


                        System.out.println("Total messages sent: " + msg.returnTotalMessages());
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
// --- MESSAGING MENU  ---
        if(loggedIn){
            System.out.println("Welcome to ChatApp.");

            boolean running = true;

            while (running) {
                // Display the main menu
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        // Ask how many messages the user wants to send
                        System.out.print("How many messages would you like to send? ");
                        int numMessages = scanner.nextInt();
                        scanner.nextLine(); // clear buffer

                        Message msg = null;

                        // For loop runs exactly numMessages times
                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1;
                            System.out.println("\n--- Message " + messageNumber + " ---");

                            // Step 1: Get and validate recipient
                            System.out.print("Enter recipient cell number: ");
                            String recipient = scanner.nextLine();

                            // Create a temporary message object to validate recipient
                            msg = new Message(messageNumber, recipient, "");
                            String recipientResult = msg.checkRecipientCell(recipient);
                            System.out.println(recipientResult);

                            if (!recipientResult.equals("Cell phone number successfully captured.")) {
                                System.out.println("Skipping this message.");
                                continue;
                            }

                            // Step 2: Get and validate message text
                            System.out.print("Enter your message: ");
                            String messageText = scanner.nextLine();

                            String lengthResult = msg.checkMessageLength(messageText);
                            System.out.println(lengthResult);

                            if (!lengthResult.equals("Message ready to send.")) {
                                System.out.println("Skipping this message.");
                                continue;
                            }

                            // Step 3: Build the final Message object with all fields
                            msg = new Message(messageNumber, recipient, messageText);

                            // Step 4: Ask user what to do with the message
                            String action = msg.sentMessage(scanner);
                            System.out.println(action);

                            // Step 5: Display message details after send or store
                            if (action.equals("Message successfully sent.") ||
                                action.equals("Message successfully stored.")) {
                                System.out.println("Message ID: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                System.out.println("Recipient: " + msg.getRecipient());
                                System.out.println("Message: " + msg.getMessageText());
                            }
                        }

                        // Display total messages AFTER the for loop ends
                        if (msg != null) {
                            System.out.println("\nTotal messages sent: " + msg.returnTotalMessages());
                        }
                        break;

                    case 2:
                        // Show recently sent messages
                        Message display = new Message();
                        System.out.println(display.printMessages());
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

       


