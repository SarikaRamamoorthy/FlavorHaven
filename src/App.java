import DatabaseAccessObjects.DBConnection;
import Utility.ExceptionHandler;
import Views.AdminView;

public class App {  
    public static void main(String[] args) {
        DBConnection.startConnection();

        AdminView.adminScreen();
    }
}
