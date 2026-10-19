package com.mycompany.part1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {

    public static final int MAX_MESSAGE_LENGTH = 250;
    public static final String JSON_FILE = "messages.json";

    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;

    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    // Used by the app: the message ID is random
    public Message(int messageNumber, String recipient, String messageText) {
        this(generateMessageID(), messageNumber, recipient, messageText);
    }

    // Used by the tests so the ID is known
    public Message(String messageID, int messageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // ===== Message ID =====
    public static String generateMessageID() {
        Random random = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += random.nextInt(10);
        }
        return id;
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String getMessageIDCreatedMessage() {
        return "Message ID generated: " + messageID;
    }

    // ===== Recipient (reuses Part 1 check) =====
    public int checkRecipientCell() {
        Login login = new Login();
        return login.checkCellPhoneNumber(recipient) ? 1 : 0;
    }

    public String getRecipientCellMessage() {
        if (checkRecipientCell() == 1) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // ===== Message length =====
    public boolean checkMessageLength() {
        return messageText != null && messageText.length() <= MAX_MESSAGE_LENGTH;
    }

    public String getMessageLengthMessage() {
        if (checkMessageLength()) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - MAX_MESSAGE_LENGTH;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    // ===== Message hash =====
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        String hash = messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // ===== Send / Disregard / Store =====
    public String sentMessage(int option) {
        switch (option) {
            case 1:
                sentMessages.add(this);
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storedMessages.add(this);
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    public String printMessage() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    // ===== Save stored messages to a JSON file =====
    public static boolean storeMessage() {
        String json = "[\n";
        for (int i = 0; i < storedMessages.size(); i++) {
            Message m = storedMessages.get(i);
            json += "  {\"messageID\": \"" + m.messageID + "\", "
                  + "\"messageHash\": \"" + m.messageHash + "\", "
                  + "\"recipient\": \"" + m.recipient + "\", "
                  + "\"message\": \"" + m.messageText.replace("\"", "\\\"") + "\"}";
            if (i < storedMessages.size() - 1) {
                json += ",";
            }
            json += "\n";
        }
        json += "]";

        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            writer.write(json);
            return true;
        } catch (IOException e) {
            System.out.println("Could not save messages: " + e.getMessage());
            return false;
        }
    }

    // Used by the tests so each test starts from zero
    public static void reset() {
        sentMessages.clear();
        storedMessages.clear();
        totalMessagesSent = 0;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
}