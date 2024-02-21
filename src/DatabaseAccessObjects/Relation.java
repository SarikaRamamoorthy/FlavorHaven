package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

import Utility.ExceptionHandler;

class Relation {
    private String tableName = "";
    private HashMap<Integer, String> tableAttributes = null;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        try {
            setTableNameHandler(tableName);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setTableNameHandler(String tableName) throws Exception {
        if (tableName != null && (!tableName.isEmpty())) {
            this.tableName = tableName;
        } else {
            throw new Exception("Table Name is Invalid");
        }
    }

    public HashMap<Integer, String> getTableAttributes() {
        return tableAttributes;
    }

    public void setTableAttributes(HashMap<Integer, String> tableAttributes) {
        try {
            setTableAttributesHandler(tableAttributes);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setTableAttributesHandler(HashMap<Integer, String> tableAttributes) throws Exception {
        if (tableAttributes.size() != 0 && tableAttributes != null) {
            this.tableAttributes = tableAttributes;
        } else {
            throw new Exception("Table Attributes are invalid");
        }
    }

    public ArrayList<String> getColumnNames(String tableName) {
        ArrayList<String> columnNames = null;
        ResultSet res = DBConnection.excecuteSelect("*", tableName, null);
        try {
            ResultSetMetaData data = res.getMetaData();
            int columnCount = data.getColumnCount();

            columnNames = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(data.getColumnName(i));
            }

            if (columnNames.isEmpty()) {
                throw new Exception("Table columns empty");
            }
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }

        return columnNames;
    }
}
