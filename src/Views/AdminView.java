package Views;

import Controllers.AdminInfoController;
import Controllers.DeskController;
import Controllers.DishesController;
import Controllers.Table;
import Controllers.VarietyController;
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
        String userName = console.readLine("User Name : ");
        String password = String.valueOf(console.readPassword("Password : "));

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
                    deskOperations();
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
                // TODO: Remove after finishing project
                e.printStackTrace();
                ExceptionHandler.invalidOptionException("Choose from (1/2/3/4/5)");
                ;
            }
        }
    }
    
    public static void dishOperations() {
        while (true) {
            Screen.clearScreen();

            Table dishTable = DishesController.returnAllDishes();
            try {
                dishTable.printTable();
            } catch (Exception e) {
                ExceptionHandler.specialExceptions("No data present");
            }

            System.out.println();
            System.out.println();
            System.out.println("1. Add a dish");
            System.out.println("2. Modify a dish");
            System.out.println("3. Remove a dish");
            System.out.println("4. Exit");
            try {
                int option = Integer.parseInt(console.readLine("Choose from (1/2/3/4): "));
                System.out.println();
                System.out.println();
                if (option == 1) {
                    
                    System.out.println("Adding a new Dish");
                    
                    Table typeTable = VarietyController.returnAllVarieties();
                    typeTable.printTable();
                    
                    String dishName = console.readLine("Enter Dish Name: ");
                    int price = Integer.parseInt(console.readLine("Enter Dish Price: "));
                    int typeId = Integer.parseInt(console.readLine("Enter Dish type (1/2/3/4/5) : "));
                    
                    boolean result = DishesController.addNewDish(dishName, price, typeId);
                    
                    if (result) {
                        console.readLine("Succesfully added :) Press Enter to continue");
                    }
                    
                }
                
                else if (option == 2) {
                    System.out.println("Modifying a dish: ");
                    int dishId = Integer.parseInt(console.readLine("Enter Dish ID: "));
                    System.out.println();
                    System.out.println();
                    
                    Table typeTable = VarietyController.returnAllVarieties();
                    typeTable.printTable();
                    
                    boolean valid = DishesController.isValidDish(dishId);
                    if (valid) {
                        System.out.println("1. Modify Name");
                        System.out.println("2. Modify Price");
                        System.out.println("3. Modify Type");
                        System.out.println("4. Exit");
                        int modifyOption = Integer.parseInt(console.readLine("Choose (1/2/3/4): "));
                        boolean result = false;
                        if (modifyOption == 1) {
                            String modifyName = console.readLine("Enter new Dish Name: ");
                            result = DishesController.modifyDish(dishId, modifyName, -1, -1);
                        }
                        
                        else if (modifyOption == 2) {
                            int price = Integer.parseInt(console.readLine("Enter new Price: "));
                            result = DishesController.modifyDish(dishId, null, price, -1);
                        }
                        
                        else if (modifyOption == 3) {
                            int type = Integer.parseInt(console.readLine("Enter a valid type(1/2/3/4/5) : "));
                            result = DishesController.modifyDish(dishId, null, -1, type);
                        }
                        
                        else if (modifyOption == 4) {
                            break;
                        }
                        
                        else {
                            ExceptionHandler.invalidOptionException("Choose from (1/2/3/4) ");
                        }
                        
                        if (result) {
                            console.readLine("Dish Modified :) Press Enter");
                        }
                    }
                    
                    else {
                        ExceptionHandler.specialExceptions("Dish ID not found");
                    }
                }
                
                else if (option == 3) {
                    System.out.println("Removing a dish: ");
                    int dishId = Integer.parseInt(console.readLine("Enter a Dish ID: "));
                    
                    boolean valid = DishesController.isValidDish(dishId);
                    
                    if (valid) {
                        boolean result = DishesController.removeDish(dishId);
                        if (result) {
                            console.readLine("Dish Removed :) Press Enter ");
                        }
                    }
                    
                    else {
                        ExceptionHandler.specialExceptions("Dish ID not found");
                    }
                }

                else if (option == 4) {
                    break;
                }
                
                else {
                    ExceptionHandler.invalidOptionException("Choose from (1/2/3/4)");
                }
            } catch (NumberFormatException e) {
                ExceptionHandler.invalidOptionException("Choose a valid Integer");
            } catch (Exception e) {
                ExceptionHandler.specialExceptions(e.getMessage());
            }
        }
    }
    
    
    private static void deskOperations() {
        Screen.clearScreen();
        Table deskTable = DeskController.returnAllDesks();
        try {
            deskTable.printTable();
        } catch (Exception e) {
            ExceptionHandler.specialExceptions("No desks available");
        }

        console.readLine("Press Enter to Exit");
    }
}
