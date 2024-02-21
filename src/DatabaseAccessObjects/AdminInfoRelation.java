package DatabaseAccessObjects;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DatabaseModel.Admin;
import Utility.ExceptionHandling;

public class AdminInfoRelation extends Relation{

    private static String SALT = "1234";
    private static ArrayList<Admin> admins;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        admins = new ArrayList<>();

        setTableName("admin_info");
        ArrayList<String> columnNames = getColumnNames(getTableName());
        for (int i = 0; i < columnNames.size(); i++) {
            map.put(i, columnNames.get(i));
        }
        setTableAttributes(map);

        // Initializing admin relation
        initializeAdmins();
    }
    
    private static void initializeAdmins() {
        
        try {
            ResultSet results = DBConnection.excecuteSelect("*", getTableName(), null);
            while(results.next()) {
                Admin admin = new Admin(results.getInt(1), results.getString(2), results.getString(3));
                admins.add(admin);
            }
        } catch (Exception e) {
            ExceptionHandling.emptyTableException(getTableName());
        }

    }

    public static String generateMD5Hash(String input){
 
        MessageDigest digest= null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        digest.update((SALT+input).getBytes());
 
        byte[] messageDigest = digest.digest();
 
        String md5 = toHex(messageDigest).toString();
 
        System.out.println(md5);
 
        return md5;       
    }

    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "x", bi);
    }
}
