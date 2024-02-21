import DatabaseAccessObjects.DBConnection;
import Utility.ExceptionHandling;
import Views.AdminLogin;

public class App {  
    public static void main(String[] args) {
        DBConnection.startConnection();

        AdminLogin.adminScreen();
    }
}
