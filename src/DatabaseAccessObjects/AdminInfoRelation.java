package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;

import DatabaseModel.Admin;
import Utility.ExceptionHandler;

public class AdminInfoRelation extends Relation {

    private static AdminInfoRelation adminRelation;
    private ArrayList<Admin> admins;

    public static AdminInfoRelation getInstance() {
        if(adminRelation == null) {
            adminRelation = new AdminInfoRelation();
        }
        return adminRelation;
    }

    private AdminInfoRelation() {
        admins = new ArrayList<>();

        setTableName("admin_info");
        setTableAttributes(getTableName());

        // Initializing admin relation
        initializeAdmins();
    }

    private void initializeAdmins() {

        try {
            ResultSet results = DBConnection.excecuteSelect("*", getTableName(), null);
            while (results.next()) {
                Admin admin = new Admin(results.getInt(1), results.getString(2), results.getString(3));
                admins.add(admin);
            }
        } catch (Exception e) {
            ExceptionHandler.emptyTableException(getTableName());
        }

    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }
}
