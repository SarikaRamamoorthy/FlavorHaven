package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;

import DatabaseModel.Variety;

public class VarietyRelation extends Relation {

    private static VarietyRelation varietyRelation;
    private static ArrayList<Variety> varieties;

    public static VarietyRelation getInstance() {
        if(varietyRelation == null) {
            varietyRelation = new VarietyRelation();
        }
        return varietyRelation;
    }

    private VarietyRelation() {
        varieties = new ArrayList<>();

        setTableName("variety");
        setTableAttributes(getTableName());

        initializeVarieties();
    }

    private void initializeVarieties() {
        try {
            ResultSet results = DBConnection.excecuteSelect("*", getTableName(), null);

            while (results.next()) {
                Variety variety = new Variety(results.getInt(1), results.getString(2));

                varieties.add(variety);
            }
            
        } catch (Exception e) {
            
        }
    }

    public ArrayList<Variety> getVarieties() {
        return varieties;
    }
}
