import DatabaseAccessObjects.DBConnection;
import Views.AdminView;

public class App {  
    public static void main(String[] args) {
        DBConnection.startConnection();
        AdminView.adminScreen();
    }
}
