package DatabaseAccessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utility.Envir;
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
            Class.forName(Envir.DRIVER_CLASS);
            con = DriverManager.getConnection(Envir.URL, Envir.USER, Envir.PASSWORD);
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