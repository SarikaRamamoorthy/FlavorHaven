package DatabaseAccessObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminInfoRelation extends Relation{

    static {
        setTableName("admin_info");
        HashMap<Integer, String> map = new HashMap<>();
        ArrayList<String> columnNames = getColumnNames(getTableName());
        for (int i = 0; i < columnNames.size(); i++) {
            map.put(i, columnNames.get(i));
        }
        setTableAttributes(map);
    }
    
}
