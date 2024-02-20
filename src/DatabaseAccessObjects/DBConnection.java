package DatabaseAccessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utility.ExceptionHandling;

public class DBConnection {
    private static Connection con = null;
    private static Statement stmt = null;

    public static void startConnection() {
        if(con == null) {
            establishConnection();
        }
    }

    private static void establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flavorhaven","root","123456789");
            stmt = con.createStatement();
        } catch (Exception e) {
            ExceptionHandling.specialExceptions(e.getMessage());
        }
    }

    public static ResultSet excecuteSelect (String columnNames, String tableName, String whereCondition) {
        String query = "SELECT "+columnNames+" FROM "+tableName;
        if(whereCondition != null){
            query += " WHERE "+whereCondition;
        }
        ResultSet res = null;
        try {
            res = stmt.executeQuery(query);
        } catch (SQLException e) {
            ExceptionHandling.specialExceptions(e.getMessage());
        }

        return res;
    }

    
}
