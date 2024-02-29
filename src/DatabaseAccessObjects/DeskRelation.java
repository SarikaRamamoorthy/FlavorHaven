package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DatabaseModel.Desk;
import Utility.ExceptionHandler;

public class DeskRelation extends Relation {

    private static DeskRelation deskRelation;
    private static ArrayList<Desk> desks;

    public static DeskRelation getInstance() {
        if(deskRelation == null) {
            deskRelation = new DeskRelation();
        }
        return deskRelation;
    }

    private DeskRelation() {
        desks = new ArrayList<>();
        
        setTableName("desk");
        setTableAttributes(getTableName());

        intializeDesks();
    }

    private void intializeDesks() {
        try {
            ResultSet results = DBConnection.excecuteSelect("*", getTableName(), null);
            while (results.next()) {
                Desk desk = new Desk(results.getInt(1), results.getString(2), results.getInt(3), results.getBoolean(4), results.getInt(5));

                desks.add(desk);
            }
        } catch (Exception e) {
            
        }
    }

    public ArrayList<Desk> getDesks() {
        return desks;
    }

    public boolean reserveDeskInRelation(Desk desk, boolean reserveValue) {
        HashMap<Integer, String> columns = getTableAttributes();
        
        String setValues = columns.get(3) + " = " + reserveValue;
        String whereCondition = columns.get(0) + " = " + desk.getDeskId();

        boolean result = DBConnection.excecuteUpdateOne(getTableName(), setValues, whereCondition);

        return result;
    }

    public boolean updatePayInRelation(Desk desk) {

        HashMap<Integer, String> columns = getTableAttributes();
        try {
            String setValues = columns.get(4) + " = " + desk.getOrderAmount();
            String whereCondition = columns.get(0) + " = " + desk.getDeskId();
            
            boolean result = DBConnection.excecuteUpdateOne(getTableName(), setValues, whereCondition);
            
            if(result)
            desk.setOrderAmount(desk.getOrderAmount());
            
            return result;
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
            return false;
        }
    }
    
    public boolean markDeskAsPaidInRelation(Desk desk) {
        HashMap<Integer, String> columns = getTableAttributes();
        try {
            
            String setValues = columns.get(4) + " = 0";
            String whereCondition = columns.get(0) + " = " + desk.getDeskId();

            boolean result = DBConnection.excecuteUpdateOne(getTableName(), setValues, whereCondition);

            if(result)
            desk.setOrderAmount(0);

            return result;

        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
            return false;
        }
    }
}
