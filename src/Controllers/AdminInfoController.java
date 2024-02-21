package Controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import DatabaseAccessObjects.AdminInfoRelation;
import DatabaseModel.Admin;
import Utility.ExceptionHandler;

public class AdminInfoController {

    private static final int ADMINID_PLACEHOLDER = 13;
    private static String SALT = "1234";
    private static AdminInfoRelation adminInfoRelation;
    private static ArrayList<Admin> admins;

    static {
        adminInfoRelation = AdminInfoRelation.getInstance();
        admins = adminInfoRelation.getAdmins();
    }

    public static boolean verifyLogin(String adminUserName, String adminPassword) {
        Admin admin = new Admin();
        try {
            admin.setAdminId(ADMINID_PLACEHOLDER);
            admin.setAdminUserName(adminUserName);
            admin.setAdminPassword(generateMD5Hash(adminPassword));
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
            return false;
        }
        return admins.contains(admin);
    }

    private static String generateMD5Hash(String input) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        digest.update((SALT + input).getBytes());

        byte[] messageDigest = digest.digest();

        String md5 = toHex(messageDigest).toString();

        return md5;
    }

    private static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "x", bi);
    }
}
