package Controllers;

import java.util.ArrayList;

import DatabaseAccessObjects.VarietyRelation;
import DatabaseModel.Variety;

public class VarietyController {

    private static VarietyRelation varietyRelation;
    private static ArrayList<Variety> varieties;

    static {
        varietyRelation = VarietyRelation.getInstance();
        varieties = varietyRelation.getVarieties();
    }

    public static Table returnAllVarieties() {
        ArrayList<String> columns = new ArrayList<>(varietyRelation.getTableAttributes().values());

        String[] headers = columns.toArray(new String[columns.size()]);

        String[][] data = new String[varieties.size()][];

        for (int i = 0; i < varieties.size(); i++) {
            String[] varietyRow = varieties.get(i).toString().split(Variety.seperator);
            data[i] = varietyRow;
        }

        Table table = new Table(headers, data);
        return table;
    }
}
