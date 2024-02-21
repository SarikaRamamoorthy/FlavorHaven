package Controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import DatabaseAccessObjects.AdminInfoRelation;
import DatabaseModel.Admin;

public class AdminInfoController {

    private static String SALT = "1234";
    private static ArrayList<Admin> admins = AdminInfoRelation.getAdmins();

    public static boolean verifyLogin(String adminUserName, String adminPassword) {
        Admin admin = new Admin(13, adminUserName, generateMD5Hash(adminPassword));
        return admins.contains(admin);
    }

    private static String generateMD5Hash(String input){
 
        MessageDigest digest= null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        digest.update((SALT+input).getBytes());
 
        byte[] messageDigest = digest.digest();
 
        String md5 = toHex(messageDigest).toString();
 
        return md5;       
    }

    private static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "x", bi);
    }
}
