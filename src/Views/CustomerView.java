package Views;

import Controllers.DeskController;
import Controllers.DishesController;
import Controllers.OrdersController;
import Controllers.Table;
import Utility.ExceptionHandler;

public class CustomerView implements Screen {

    public static int CURRENT_DESK_ID = -1;

    public static void customerScreen() {
        while (true) {
            Screen.clearScreen();
            Screen.titleScreen();

            // Displaying available tables
            Table deskTables = DeskController.returnAllAvailableDesks();
            try {
                deskTables.printTable();
            } catch (Exception e) {
                ExceptionHandler.specialExceptions("All tables are filled Sorry :(");
                break;
            }

            System.out.println("1. Reserve a table");
            System.out.println("2. Exit");
            try {
                int option = Integer.parseInt(console.readLine("Choose from option (1/2) : "));
                if (option == 1) {
                    int deskId = Integer.parseInt(console.readLine("Enter the desk-id: "));
                    boolean validDesk = DeskController.isValidDesk(deskId);

                    if (validDesk) {
                        boolean reservation = DeskController.reserveDesk(deskId, true);
                        if(reservation) {
                            console.readLine("Enjoy your meal :) Press Enter to Continue : ");
                            CURRENT_DESK_ID = deskId;
                            viewMenu();
                        }
                        else {
                            ExceptionHandler.specialExceptions("Unable to Reserve Desk");
                        }
                    } else {
                        ExceptionHandler.specialExceptions("Desk Not found");
                    }
                }

                else if (option == 2) {
                    break;
                }

                else {
                    ExceptionHandler.invalidOptionException("Choose from (1/2) ");
                }
            } catch (NumberFormatException e) {
                ExceptionHandler.specialExceptions("Choose from (1/2) ");
            } catch (Exception e) {
                ExceptionHandler.specialExceptions(e.getMessage());
            }
        }
    }

    private static void viewMenu() {
        while (true) {
            Screen.clearScreen();
            boolean waitingForPayment = DeskController.hasAlreadyPaid(CURRENT_DESK_ID);

            if(waitingForPayment) {
                console.readLine("Please wait while we process your payment :) Press Enter ");
                continue;
            }

            System.out.println("Our Menu");
            System.out.println("1. Starter");
            System.out.println("2. Vegetarian");
            System.out.println("3. Non Vegetarian");
            System.out.println("4. Dessert");
            System.out.println("5. Beverage");
            System.out.println("6. View Orders");
            System.out.println("7. Finish Order"); // TODO: Exit condition
            System.out.println();
            try {
                int option = Integer.parseInt(console.readLine("Choose from (1/2/3/4/5/6/7) : "));

                if (option == 1) {
                    showVariety(option, "starter");
                }

                else if (option == 2) {
                    showVariety(option, "vegetarian");
                }

                else if (option == 3) {
                    showVariety(option, "non vegetarian");
                }

                else if (option == 4) {
                    showVariety(option, "dessert");
                }

                else if (option == 5) {
                    showVariety(option, "beverage");
                }

                else if (option == 6) {
                    try {
                        Table currentOrderTable = OrdersController.returnCurrentOrders(CURRENT_DESK_ID);
                        currentOrderTable.printTable();
                        console.readLine("Press Enter to Continue ");
                    } catch (Exception e) {
                        ExceptionHandler.specialExceptions("You have not Ordered Anything yet :( ");
                    }
                }

                else if (option == 7) {
                    boolean result = OrdersController.isOrderFinished(CURRENT_DESK_ID);

                    if(!result) {
                        console.readLine("Your order is not complete Press Enter to Continue : ");
                    }

                    else {
                        Table billTable = OrdersController.returnBill(CURRENT_DESK_ID);
                        billTable.printTable();
                        console.readLine("Press Enter to Pay :) ");

                        boolean paymentCheck = DeskController.payAmount(CURRENT_DESK_ID);

                        if(paymentCheck) {
                            console.readLine("Your payment has been accepted Please wait while we process it :) Press Enter .. ");
                            continue;
                        }
                        else {
                            console.readLine("Something went wrong with your payment, please try again ... ");
                        }
                    }
                }

            } catch (NumberFormatException e) {
                ExceptionHandler.invalidOptionException("Choose from (1/2/3/4/5/6/7) ");
            } catch (Exception e) {
                ExceptionHandler.specialExceptions(e.getMessage());
            }
        }
    }
    
    private static void showVariety(int type, String typeName) throws Exception {
        Screen.clearScreen();
        Table starterTable = DishesController.returnCustomerDish(type);
        try {
            starterTable.printTable();
        } catch (Exception e) {
            ExceptionHandler.specialExceptions("No " + typeName + " dishes available Sorry :( ");
        }

        int dishId = Integer.parseInt(console.readLine("Enter a Dish Id: "));

        try {
            boolean result = DishesController.isValidDish(dishId, type); // Starter Id = 1;
            if (!result)
                throw new Exception("Not a valid " + typeName + " dish");
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
            return;
        }

        /**
         * Order states
         * -1 -> Not ordered
         * 0 -> ordered but not served (increase or descrease)
         * 1 -> ordered and served (reordering)
         */

        int ordered = OrdersController.wasPreviouslyOrdered(CURRENT_DESK_ID, dishId, dishId, false);

        if (ordered == -1) {
            int quantity = Integer.parseInt(console.readLine("Enter the quantity : "));
    
            try {
                String choice = console.readLine("Confirm Order (y/n) : ").toLowerCase();
    
                if (choice.length() != 1) {
                    throw new Exception("Choose from (y/n) ");
                }

                if (choice.charAt(0) != 'y') {
                    return;
                }
            } catch (Exception e) {
                ExceptionHandler.specialExceptions(e.getMessage());
            }

            boolean result = OrdersController.addOrder(CURRENT_DESK_ID, dishId, quantity);
            
            if (result) {
                console.readLine("Added Order successfully");
            }
        }
        
        else if(ordered == 0) {
            try {
                String choice = console.readLine("You have already ordered, do you want to increase the quantity (y/n) : ").toLowerCase();
                
                if (choice.length() != 1) {
                    throw new Exception("Choose from (y/n) ");
                }
    
                if (choice.charAt(0) != 'y') {
                    return;
                }
    
                int quantity = Integer.parseInt(console.readLine("Enter the increase from the previous quanity: "));
    
                if(quantity <= 0) {
                    throw new Exception("Quantity cannot be negative or zero");
                }

                boolean result = OrdersController.updateOrder(CURRENT_DESK_ID, dishId, quantity, false);

                if(result) {
                    console.readLine("Updated Order Successfully");
                }
                
            } catch (Exception e) {
                ExceptionHandler.specialExceptions(e.getMessage());
            }
        }
        
        else if(ordered == 1) {
            try {
                String choice = console.readLine("You have already ordered, do you want to reorder (y/n) : ").toLowerCase();
                
                if (choice.length() != 1) {
                    throw new Exception("Choose from (y/n) ");
                }
                
                if (choice.charAt(0) != 'y') {
                    return;
                }
                
                int quantity = Integer.parseInt(console.readLine("Enter the quantity: "));
                
                if(quantity <= 0) {
                    throw new Exception("Quantity cannot be negative or zero");
                }
                
                boolean result = OrdersController.updateOrder(CURRENT_DESK_ID, dishId, quantity, false);
        
                if(result) {
                    console.readLine("Updated Order Successfully");
                }
                
            } catch (Exception e) {
                ExceptionHandler.specialExceptions(e.getMessage());
            }
        }

    }
}
