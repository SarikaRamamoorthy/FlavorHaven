package Views;

import java.io.Console;

import Utility.InputConsole;

/**
 * Screen
 */
public interface Screen {
    
    public static Console console = InputConsole.console;

    static void titleScreen() {
        System.out.println("\r\n" + //
        " ____  __     __   _  _   __  ____    _  _   __   _  _  ____  __ _ \r\n" + //
        "(  __)(  )   / _\\ / )( \\ /  \\(  _ \\  / )( \\ / _\\ / )( \\(  __)(  ( \\\r\n" + //
        " ) _) / (_/\\/    \\\\ \\/ /(  O ))   /  ) __ (/    \\\\ \\/ / ) _) /    /\r\n" + //
        "(__)  \\____/\\_/\\_/ \\__/  \\__/(__\\_)  \\_)(_/\\_/\\_/ \\__/ (____)\\_)__)\r\n" + //
        "");
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void clearLine(int lineCount){
        for (int i = 0; i < lineCount; i++) {
            System.out.print(String.format("\033[%dA",1));
            System.out.print("\033[2K");
        }
    }
    
} 
