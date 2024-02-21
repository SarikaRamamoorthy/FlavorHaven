package Views;

public class AdminLogin implements Screen {
    public void adminScreen() {
        clearScreen();
        decor();
        System.out.print("User Name : ");
        String adminName = System.console().readLine();
        System.out.println();
        System.out.print("Password : ");
        String password = String.valueOf(System.console().readPassword());
    }


    
}
