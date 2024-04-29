package za.ac.tut.kotashop.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class PasswordEncryptor {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPasswordBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPasswordBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean verifyPassword(String enteredPassword, String hashedPassword) {
        String enteredPasswordHash = hashPassword(enteredPassword);

        System.out.println("enteredPasswordHash : " + enteredPasswordHash);
        System.out.println("hashedPassword "+ hashedPassword);


        return hashedPassword.equals(enteredPasswordHash);
    }
}
