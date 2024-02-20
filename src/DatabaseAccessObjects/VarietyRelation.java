package DatabaseAccessObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class VarietyRelation extends Relation {
    static {
        setTableName("VarietyRelation");
        ArrayList<String> columnNames = getColumnNames(getTableName());
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < columnNames.size(); i++) {
            map.put(i, columnNames.get(i));
        }
        setTableAttributes(map);
    }
}
