package Views;

import com.jakewharton.fliptables.FlipTable;

import Controllers.AdminInfoController;
import Controllers.DishesController;
import Controllers.Table;
import Utility.ExceptionHandler;

public class AdminView implements Screen {

    public static void adminScreen() {
        while (true) {
            Screen.clearScreen();
            Screen.titleScreen();
            System.out.println("1. Login");
            System.out.println("2. Exit");
            try {
                int option = Integer.parseInt(console.readLine("Choose (1/2): "));
                if (option == 1) {
                    loginScreen();
                } else if (option == 2) {
                    console.readLine("Exiting Application ... (Press Enter)");
                    break;
                } else {
                    ExceptionHandler.invalidOptionException("Choose from (1/2)");
                }
            } catch (Exception e) {
                ExceptionHandler.invalidOptionException("Choose from (1/2)");
            }
        }

    }

    public static void loginScreen() {
        Screen.clearScreen();
        System.out.print("User Name : ");
        String userName = console.readLine();
        System.out.println();
        System.out.print("Password : ");
        String password = String.valueOf(console.readPassword());

        boolean result = AdminInfoController.verifyLogin(userName, password);
        if (result) {
            console.readLine("Login Successful Press Enter to Continue");
            adminOperations();
        } else {
            System.out.println("Login Failed Press Enter to Continue");
        }
    }

    private static void adminOperations() {
        while (true) {
            Screen.clearScreen();
            System.out.println("1. Dishes Collection");
            System.out.println("2. View all Desks");
            System.out.println("3. View unserved Orders");
            System.out.println("4. View unprocessed bills");
            System.out.println("5. Exit");
            try {
                int option = Integer.parseInt(console.readLine("Choose from (1/2/3/4/5): "));
                if (option == 1) {
                    // Display dishes

                    Table table = DishesController.returnAllDishes();
                    System.out.println(FlipTable.of(table.getHeaders(), table.getData()));
                    console.readLine("Press Enter");
                }

                else if (option == 2) {
                    // View All desks

                    
                }

                else if (option == 3) {
                    // View unserved orders
                }

                else if (option == 4) {
                    // View unprocessed bills
                }

                else if (option == 5) {
                    break;
                }

                else {
                    ExceptionHandler.invalidOptionException("Choose from (1/2/3/4/5)");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ExceptionHandler.invalidOptionException("Choose from (1/2/3/4/5)");
                ;
            }
        }
    }
}
