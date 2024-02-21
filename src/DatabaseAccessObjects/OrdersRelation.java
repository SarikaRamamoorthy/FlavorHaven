package DatabaseAccessObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class OrdersRelation extends Relation {
    private static OrdersRelation ordersRelation;

    public static OrdersRelation getInstance() {
        if(ordersRelation == null) {
            ordersRelation = new OrdersRelation();
        }
        return ordersRelation;
    }

    private OrdersRelation() {
        setTableName("orders");
        ArrayList<String> columnNames = getColumnNames(getTableName());
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < columnNames.size(); i++) {
            map.put(i, columnNames.get(i));
        }
        setTableAttributes(map);
    }
}
