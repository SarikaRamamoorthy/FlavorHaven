package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

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
        ArrayList<String> columnNames = getColumnNames(getTableName());
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < columnNames.size(); i++) {
            map.put(i, columnNames.get(i));
        }
        setTableAttributes(map);

        intializeDesks();
    }

    private void intializeDesks() {
        try {
            ResultSet results = DBConnection.excecuteSelect("*", getTableName(), null);
            while (results.next()) {
                Desk desk = new Desk(results.getInt(1), results.getString(2), results.getBoolean(3));

                desks.add(desk);
            }
        } catch (Exception e) {
            
        }
    }

    public ArrayList<Desk> getDesks() {
        return desks;
    }
}
