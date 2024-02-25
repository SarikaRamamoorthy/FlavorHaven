package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

    public void setTableAttributes(String tableName) {
        ResultSet res = DBConnection.excecuteSelect("*", tableName, null);
        try {
            ResultSetMetaData data = res.getMetaData();
            int columnCount = data.getColumnCount();
            
            tableAttributes = new HashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                tableAttributes.put(i-1, data.getColumnName(i));
            }

            if (tableAttributes.isEmpty()) {
                throw new Exception("Table columns empty");
            }
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }
}
