package DatabaseAccessObjects;

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
        setTableAttributes(getTableName());
    }
}
