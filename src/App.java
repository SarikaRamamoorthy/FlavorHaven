import DatabaseAccessObjects.DBConnection;
import Views.AdminView;
import Views.CustomerView;

public class App {  
    public static void main(String[] args) {
        DBConnection.startConnection();
        AdminView.adminScreen();
        // CustomerView.customerScreen();
    }
}
