package Views;

import Controllers.AdminInfoController;

public class AdminLogin implements Screen {
    public static void adminScreen() {
        Screen.clearScreen();
        Screen.titleScreen();
        System.out.print("User Name : ");
        String userName = console.readLine();
        System.out.println();
        System.out.print("Password : ");
        String password = String.valueOf(console.readPassword());
        
        boolean result = AdminInfoController.verifyLogin(userName, password);
        if(result) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed");
        }
    }
}
