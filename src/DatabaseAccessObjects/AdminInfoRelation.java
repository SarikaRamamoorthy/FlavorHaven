package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

import Utility.ExceptionHandling;

public class AdminInfoRelation extends Relation{
    static String tableName = "";
    static HashMap<Integer, String> tableAttributes = null;

    static {
        tableName = "admininfo";
        tableAttributes = new HashMap<>();
        ArrayList<String> columnNames = getColumnNames();
        for (int i = 0; i < columnNames.size(); i++) {
            tableAttributes.put(i, columnNames.get(i));
        }
    }

    public static ArrayList<String> getColumnNames() {
        ArrayList<String> columnNames = null;
        ResultSet res = DBConnection.excecuteSelect("*", tableName, null);
        try {
            ResultSetMetaData data = res.getMetaData();
            int columnCount = data.getColumnCount();

            columnNames = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(data.getColumnName(i));
            }

            if(columnNames.isEmpty()){
                throw new Exception("Table columns empty");
            }
        } catch (Exception e) {
            ExceptionHandling.specialExceptions(e.getMessage());
        }

        return columnNames;
    }
}
