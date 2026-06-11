package com.mycompany.mychatapp;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("======== Registration ==========");

        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = input.nextLine();

            if (login.checkUserName(username)) {
                break;
            }
            System.out.println("Username is not correctly formatted (must contain _ and be <= 5 characters).");
        }

        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) {
                break;
            }
            System.out.println("Password must be 8+ chars, 1 uppercase, 1 number, 1 special character.");
        }

        String phone;
        while (true) {
            System.out.print("Enter phone number (+27...): ");
            phone = input.nextLine();

            if (login.checkCellPhoneNumber(phone)) {
                break;
            }
            System.out.println("Phone number must start with +27 and be max 12 characters.");
        }

        String response = login.registerUser(username, password, phone);
        System.out.println(response);

        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));

        if (loggedIn) {
            Message.loadStoredMessages();
            System.out.println("Welcome to ChatApp.");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        sendMessages(scanner);
                        break;

                    case 2:
                        System.out.println(Message.printMessages());
                        break;

                    case 3:
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    case 4:
                        showStoredMessagesMenu(scanner);
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }

            scanner.close();
        } else {
            System.out.println("Login failed. Exiting.");
        }

        input.close();
    }

    private static void sendMessages(Scanner scanner) {
        System.out.print("How many messages would you like to send? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();

        Message msg = null;

        for (int i = 0; i < numMessages; i++) {
            int messageNumber = i + 1;

            System.out.println("\n--- Message " + messageNumber + " ---");

            System.out.print("Enter recipient cell number: ");
            String recipient = scanner.nextLine();

            msg = new Message(messageNumber, recipient, "");

            String recipientResult = msg.checkRecipientCell(recipient);
            System.out.println(recipientResult);

            if (!recipientResult.equals("Cell phone number successfully captured.")) {
                System.out.println("Skipping this message.");
                continue;
            }

            System.out.print("Enter your message: ");
            String messageText = scanner.nextLine();

            String lengthResult = msg.checkMessageLength(messageText);
            System.out.println(lengthResult);

            if (!lengthResult.equals("Message ready to send.")) {
                System.out.println("Skipping this message.");
                continue;
            }

            msg = new Message(messageNumber, recipient, messageText);

            String action = msg.sentMessage(scanner);
            System.out.println(action);

            if (action.equals("Message successfully sent.") || action.equals("Message successfully stored.")) {
                System.out.println("Message ID: " + msg.getMessageID());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessageText());
            }
        }

        if (msg != null) {
            System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
        }
    }

    private static void showStoredMessagesMenu(Scanner scanner) {
        int subChoice = 0;

        while (subChoice != 7) {
            System.out.println("\n--- Stored Messages ---");
            System.out.println("1 - Display all stored messages");
            System.out.println("2 - Display longest message");
            System.out.println("3 - Search by message ID");
            System.out.println("4 - Search by recipient");
            System.out.println("5 - Delete by message hash");
            System.out.println("6 - Display full report");
            System.out.println("7 - Return to main menu");
            System.out.print("Enter choice: ");

            try {
                subChoice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (subChoice) {
                case 1:
                    if (Message.getStoredMessages().isEmpty()) {
                        System.out.println("No stored messages found.");
                    } else {
                        System.out.println("=== Stored Messages ===");
                        for (String storedMsg : Message.getStoredMessages()) {
                            System.out.println("- " + storedMsg);
                        }
                    }
                    break;

                case 2:
                    System.out.println(Message.displayLongestMessage());
                    break;

                case 3:
                    System.out.print("Enter message ID to search: ");
                    String searchID = scanner.nextLine().trim();
                    System.out.println(Message.searchByMessageID(searchID));
                    break;

                case 4:
                    System.out.print("Enter recipient number to search: ");
                    String searchRecipient = scanner.nextLine().trim();
                    System.out.println(Message.searchByRecipient(searchRecipient));
                    break;

                case 5:
                    System.out.print("Enter message hash to delete: ");
                    String deleteHash = scanner.nextLine().trim();
                    System.out.println(Message.deleteByHash(deleteHash));
                    break;

                case 6:
                    System.out.println(Message.printMessages());
                    break;

                case 7:
                    System.out.println("Returning to main menu.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-7.");
            }
        }
    }
}
