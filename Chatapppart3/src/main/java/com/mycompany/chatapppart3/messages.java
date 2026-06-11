/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Asange
 */

    

public class Message {

    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageID;
    private String messageHash;

    private static int totalMessages = 0;
    private static String printedMessages = "";

    // -------------------------------------------------------------------------
    // Part 3 - Five static parallel arrays (shared across all Message objects)
    // Attribution: ArrayList from java.util standard library
    // -------------------------------------------------------------------------
 
    /** Stores the text of every message the user chose to Send */
    private static List<String> sentMessages = new ArrayList<>();
 
    /** Stores the text of every message the user chose to Discard */
    private static List<String> disregardedMessages = new ArrayList<>();
 
    /** Stores messages read back from the JSON file at startup */
    private static List<String> storedMessages = new ArrayList<>();
 
    /** Stores the hash string for every message processed (Send or Store) */
    private static List<String> messageHashes = new ArrayList<>();
 
    /** Stores the unique ID for every message processed (Send or Store) */
    private static List<String> messageIDs = new ArrayList<>();
 
    /**
     * Additional parallel list to track the recipient for each sent message.
     * Used by searchByRecipient() and printMessages() to match recipient with
     * message text at the same index.
     */
    private static List<String> recipientList = new ArrayList<>();
 
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    public String checkMessageLength(String text) {
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = text.length() - 250;
            return "Message exceeds 250 characters by " + over;
        }
    }

    public String checkRecipientCell(String recipient) {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }

    public String createMessageHash() {
        String idPart = messageID.substring(0, 2);

        String[] words = messageText.split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";

        return (idPart + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public String generateMessageID() {
        Random num = new Random();
        String id = "";

        for (int i = 0; i < 10; i++) {
            id += num.nextInt(10);
        }

        return id;
    }

    public String sentMessage(Scanner sent) {

        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1) Send message");
        System.out.println("2) Disregard message");
        System.out.println("3) Store message");

        int option = sent.nextInt();
        sent.nextLine();

        switch (option) {

            case 1:
                totalMessages++;
                printedMessages += "SENT: " + messageText + "\n";
                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipientList.add(recipient);
                return "Message successfully sent.";

            case 2:
                disregardedMessages.add(messageText);
                return "Message disregarded.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    public void storeMessage() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("messageID", this.messageID);
            obj.put("recipient", this.recipient);
            obj.put("messageText", this.messageText);
            obj.put("hash", messageHash);

            FileWriter file = new FileWriter("messages.json", true);
            file.write(obj.toString());
            file.write(System.lineSeparator());
            file.close();

        } catch (IOException e) {
            System.out.println("Error saving message");
        }
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }
    
    // -------------------------------------------------------------------------
    // Part 3 - Section 5: Load stored messages from JSON at startup
    // -------------------------------------------------------------------------
 
    /**
     * Reads messages.json line by line and loads each stored message
     * into the storedMessages array. Called once at application startup
     * after the user logs in.
     * Attribution: org.json library - https://mvnrepository.com/artifact/org.json/json
     */
    public static void loadStoredMessages() {
        try (BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    JSONObject jsonObject = new JSONObject(line);
                    String text = jsonObject.getString("messageText");
                    storedMessages.add(text);
                }
            }
        } catch (IOException e) {
            // File does not exist yet - this is expected on a fresh run
            System.out.println("No stored messages file found. Starting fresh.");
        }
    }
 
    // -------------------------------------------------------------------------
    // Part 3 - Section 3: Display longest message
    // -------------------------------------------------------------------------
 
    /**
     * Searches through the storedMessages array and returns the message
     * with the greatest number of characters.
     *
     * @return the longest stored message, or an empty string if the array is empty
     */
    public static String displayLongestMessage() {
        String longest = "";
        for (String message : storedMessages) {
            if (message.length() > longest.length()) {
                longest = message;
            }
        }
        return longest;
    }
 
    // -------------------------------------------------------------------------
    // Part 3 - Section 4: Search and Delete
    // -------------------------------------------------------------------------
 
    /**
     * Searches the messageIDs array for the given ID and returns the
     * corresponding message text from the sentMessages array at the same index.
     * Uses parallel array indexing.
     *
     * @param id the message ID to search for
     * @return the matching message text, or "Message not found." if no match
     */
    public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return sentMessages.get(i);
            }
        }
        return "Message not found.";
    }
 
    /**
     * Searches through the recipientList and returns all messages sent
     * to the given recipient. There may be multiple results.
     *
     * @param recipient the cell number to search for
     * @return a string containing all matching messages, or "No messages found."
     */
    public static String searchByRecipient(String recipient) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < recipientList.size(); i++) {
            if (recipientList.get(i).equals(recipient)) {
                results.append(sentMessages.get(i)).append("\n");
            }
        }
        if (results.length() == 0) {
            return "No messages found.";
        }
        return results.toString().trim();
    }
 
    /**
     * Finds the message matching the given hash in messageHashes and removes
     * that entry from messageHashes, sentMessages, messageIDs, and recipientList.
     * After removal, breaks immediately to avoid index-out-of-bounds errors.
     *
     * @param hash the message hash to delete
     * @return "Message: [text] successfully deleted." on success,
     *         or "Hash not found." if no match
     */
    public static String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                String deletedText = sentMessages.get(i);
                messageHashes.remove(i);
                sentMessages.remove(i);
                messageIDs.remove(i);
                recipientList.remove(i);
                return "Message: " + deletedText + " successfully deleted.";
            }
        }
        return "Hash not found.";
    }
 
    // -------------------------------------------------------------------------
    // Part 3 - Section 6: Display message report
    // -------------------------------------------------------------------------
 
    /**
     * Builds and returns a formatted report of all sent messages.
     * Each entry shows the Message Hash, Recipient, and Message text.
     * Uses parallel array indexing to retrieve all three for each message.
     *
     * @return the full formatted report as a String
     */
    public static String printMessages() {
        StringBuilder report = new StringBuilder();
        report.append("=== Message Report ===\n");
 
        if (sentMessages.isEmpty()) {
            report.append("No sent messages to display.\n");
            return report.toString();
        }
 
        for (int i = 0; i < sentMessages.size(); i++) {
            report.append("------------------------------\n");
            report.append("Hash:      ").append(messageHashes.get(i)).append("\n");
            report.append("Recipient: ").append(recipientList.get(i)).append("\n");
            report.append("Message:   ").append(sentMessages.get(i)).append("\n");
        }
        report.append("------------------------------\n");
        return report.toString();
    }
 
    // -------------------------------------------------------------------------
    // Accessors for static lists (used by unit tests)
    // -------------------------------------------------------------------------
 
    /**
     * Returns the sentMessages list (for testing purposes).
     *
     * @return the static sentMessages ArrayList
     */
    public static List<String> getSentMessages() {
        return sentMessages;
    }
 
    /**
     * Returns the disregardedMessages list (for testing purposes).
     *
     * @return the static disregardedMessages ArrayList
     */
    public static List<String> getDisregardedMessages() {
        return disregardedMessages;
    }
 
    /**
     * Returns the storedMessages list (for testing purposes).
     *
     * @return the static storedMessages ArrayList
     */
    public static List<String> getStoredMessages() {
        return storedMessages;
    }
 
    /**
     * Returns the messageHashes list (for testing purposes).
     *
     * @return the static messageHashes ArrayList
     */
    public static List<String> getMessageHashes() {
        return messageHashes;
    }
 
    /**
     * Returns the messageIDs list (for testing purposes).
     *
     * @return the static messageIDs ArrayList
     */
    public static List<String> getMessageIDs() {
        return messageIDs;
    }
 
    /**
     * Clears all static arrays. Used in unit tests to reset state between tests.
     */
    public static void clearAllArrays() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        recipientList.clear();
    }

}
