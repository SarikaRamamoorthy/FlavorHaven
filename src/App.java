import DatabaseAccessObjects.AdminInfoRelation;
import DatabaseAccessObjects.DBConnection;


public class App {
    public static void main(String[] args) {
        DBConnection.startConnection();
        AdminInfoRelation admin = new AdminInfoRelation();
        
    }
}
