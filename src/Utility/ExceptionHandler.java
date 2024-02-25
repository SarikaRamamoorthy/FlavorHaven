package Utility;

import java.io.Console;

public class ExceptionHandler {
    private static Console console = InputConsole.console;
    
    public static void specialExceptions(String ex) {
        console.readLine(ex+" Press Enter to Continue: ");
    }

    public static void emptyTableException(String table) {
        console.readLine(table + " is Empty Press Enter to Continue: ");
    }

    public static void invalidOptionException(String invalidInput) {
        console.readLine("Invalid Input: "+invalidInput+" Press Enter to try again: ");
    }
}