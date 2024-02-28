package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;

import DatabaseModel.Desk;

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

    
}
