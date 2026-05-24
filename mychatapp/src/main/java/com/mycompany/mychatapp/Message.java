/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

import java.io.FileWriter;
import java.io.IOException;
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
    private String sendMessage;
    
   private static int totalMessages = 0;
   private static String printedMessages = "";
    // this code your nmessages
    public Message(int messageNumber,String recipient, String messageText) {
        this.messageNumber = messageNumber;
        
        // thi code random ID
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    
    // check your messages length
    public String checkMessageLength(String text){
        if (text.length()<= 250) {
            return "username succesfully captured";
        } else {
            int over = text.length() -250;
            return "message exceeds 250 characters by " + over + "; please reduce the size";
        }
    }        
    
    //  check recipient nuumber
    public String checkRecipientCell(String recipient) {
        if (recipient.startsWith("+27") && recipient.length() ==12) {
            return "cellphone number sccesfully added";
        } else {
        return "cellphone number incorectly formatted or does not contain international code";
        }
    }

    // create hash
    public String createMessageHash() {
        String idPart = messageID.substring(0,2);
        String[] words = messageText.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length-1];

        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

    // check ID
    public boolean checkMesssageID() {
        if(messageID.length() ==10) {
            return true;
        } else {
            return false;
        }
    }

    public String generateMessageID() {
        Random num = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            int Number = num.nextInt(10);
            id = id + Number;
        }
        
        return id;
    }

    public String sentMessage() {
        Scanner sent = new Scanner(System.in);
        System.out.println("what would you like to do with this message?");
        System.out.println("1) send message");
        System.out.println("2)disregard message");
        System.out.println("3)store message to send later");
        System.out.print("option choice(1, 2 or 3): ");
        
        int option = sent.nextInt();
        sent.nextLine();
    // this code send messagec option
     switch(option) {

         case 1 :
            return "messagesucessfully captured.";

         case 2 :
            return "press backspace to delete";

         case 3:
             storeMessage();
            return " message succesfuly captured";

         default:
            return " Invalid option";
        }
    }

    public void storeMessage(){
        try {
           JSONObject obj = new JSONObject();
           obj.put("MessageID: ", this.messageID);
           obj.put("Recipient: ", this.recipient);
           obj.put("Message: ", this.messageText);
           obj.put("Hash: ", messageHash);
           FileWriter file = new FileWriter("Message.json", true);
           file.write(obj.toString());
           file.write(System.lineSeparator());
           file.close();
        } catch(IOException e) {
            System.out.println("Error saving message");
        }
    }
    
    
}


