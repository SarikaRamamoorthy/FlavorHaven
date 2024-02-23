package Controllers;

import com.jakewharton.fliptables.FlipTable;

import Utility.ExceptionHandler;

public class Table {
    private String[] headers;
    private String[][] data;

    public Table(String[] headers, String[][] data) {
        setHeaders(headers);
        setData(data);
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        try {
            setHeadersHandler(headers);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    public void setHeadersHandler(String[] headers) throws Exception {
        if (headers != null)
            this.headers = headers;
        else
            throw new Exception("Unable to Set empty column values ");
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        try {
            setDataHandler(data);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    public void setDataHandler(String[][] data) throws Exception {
        if (data != null)
            this.data = data;
        else
            throw new Exception("Unable to Set empty data");
    }

    public void printTable() {
        System.out.println(FlipTable.of(this.headers, this.data));
    }
}
