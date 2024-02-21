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
            console.readLine("Login Failed Press Enter to Continue");
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
                    dishOperations();
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

    public static void dishOperations() {
        while (true) {
            Screen.clearScreen();
            Table table = DishesController.returnAllDishes();
            System.out.println(FlipTable.of(table.getHeaders(), table.getData()));

            System.out.println();
            System.out.println();
            System.out.println("1. Add a dish");
            System.out.println("2. Modify a dish");
            System.out.println("3. Remove a dish");
            System.out.println("4. Exit");
            System.out.print("Choose from (1/2/3/4): ");
            try {
                int option = Integer.parseInt(console.readLine());
                if (option == 1) {
                    System.out.println("Adding a new Dish");
                    String dishName = console.readLine("Enter Dish Name: ");
                    int price = Integer.parseInt(console.readLine("Enter Dish Price: "));
                    int typeId = Integer.parseInt(console.readLine("Enter Dish type: "));

                    boolean result = DishesController.addNewDish(dishName, price, typeId);

                    if (result) {
                        console.readLine("Succesfully added :) Press Enter to continue");
                    }

                } 
                
                else if(option == 2) {
                    System.out.println("Modifying a dish");
                    int dishId = Integer.parseInt(console.readLine("Enter Dish ID: "));

                    boolean result = DishesController.modifyDish(dishId);
                }

                else if (option == 4) {
                    break;
                }
            } catch (Exception e) {
                ExceptionHandler.invalidOptionException("Choose a valid Integer");
            }
        }
    }
}
