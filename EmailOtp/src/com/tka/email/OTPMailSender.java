package com.tka.email;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class OTPMailSender {
    
    // Method to generate a random 6-digit OTP
    public static String generateOTP() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

    public static void sendEmail(String recipientEmail, String otp) {
        // Sender's email credentials
        final String senderEmail = "jagdalepratiksha11@gmail.com";  
        final String senderPassword = "hejw bhse tezr lrrq"; 

        // SMTP server settings
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your One-Time Password (OTP) is: " + otp + "\n\nDo not share this code with anyone.");

            // Send the email
            Transport.send(message);
            System.out.println("OTP sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String otp = generateOTP();
        String recipientEmail = "aanamrs08@gmail.com"; // Change this to the recipient's email

        sendEmail(recipientEmail, otp);
    }
}