package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

import Utility.ExceptionHandler;

class Relation {
    private static String tableName = "";
    private static HashMap<Integer, String> tableAttributes = null;


    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
        try {
            setTableNameHandler(tableName);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private static void setTableNameHandler(String tableName) throws Exception {
        if(tableName != null && (!tableName.isEmpty())) {
            Relation.tableName = tableName;
        }
        else {
            throw new Exception("Table Name is Invalid");
        }
    }

    public static HashMap<Integer, String> getTableAttributes() {
        return tableAttributes;
    }

    public static void setTableAttributes(HashMap<Integer, String> tableAttributes) {
        try {
            setTableAttributesHandler(tableAttributes);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private static void setTableAttributesHandler(HashMap<Integer, String> tableAttributes) throws Exception{
        if(tableAttributes.size() != 0 && tableAttributes != null){
            Relation.tableAttributes = tableAttributes;
        }
        else {
            throw new Exception("Table Attributes are invalid");
        }
    }

    public static ArrayList<String> getColumnNames(String tableName) {
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
            ExceptionHandler.specialExceptions(e.getMessage());
        }

        return columnNames;
    } 
}
