package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DatabaseModel.Desk;
import DatabaseModel.Order;

public class OrdersRelation extends Relation {

    private static OrdersRelation ordersRelation;
    private ArrayList<Order> orders;

    public static OrdersRelation getInstance() {
        if(ordersRelation == null) {
            ordersRelation = new OrdersRelation();
        }
        return ordersRelation;
    }

    private OrdersRelation() {
        orders = new ArrayList<>();

        setTableName("orders");
        setTableAttributes(getTableName());

        initializeOrders();
    }
    
    private void initializeOrders() {

        try {
            ResultSet results = DBConnection.excecuteSelect("*", getTableName(), null);
            while (results.next()) {
                Order order = new Order(results.getInt(1), results.getInt(2), results.getInt(3), results.getBoolean(4));

                orders.add(order);
            }
        } catch (Exception e) {

        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public boolean addOrderToRelation(Order order) {
        String values = order.getDeskId() + "," + order.getDishId() + "," + order.getQuantity() + "," + order.isServed();

        boolean result = DBConnection.excecuteInsertOne(getTableName(), values);

        if(result) {
            orders.add(order);
        }
        return result;
    }

    public boolean updateOrderToRelation(Order order) {
        HashMap<Integer, String> columns = getTableAttributes();
        String whereCondition = columns.get(0) + " = " + order.getDeskId() + " AND " + columns.get(1) + " = " + order.getDishId();

        String setValues = columns.get(2) + " = " + order.getQuantity() + ", " + columns.get(3) + " = " + order.isServed();

        boolean result = DBConnection.excecuteUpdateOne(getTableName(), setValues, whereCondition);

        return result;
    }

    public boolean removeOrdersInDeskInRelation(Desk desk) {
        HashMap<Integer, String> columns = getTableAttributes();
        String whereCondition = columns.get(0) + " = " + desk.getDeskId();
        boolean result = DBConnection.excecuteRemoveOne(getTableName(), whereCondition);
        
        return result;
    }
    
    public boolean markOrderAsServedInRelation(Order order) {
        HashMap<Integer, String> columns = getTableAttributes();

        String setValues = columns.get(3) + " = true";
        String whereCondition = columns.get(0) + " = " + order.getDeskId() + " AND " + columns.get(1) + " = " + order.getDishId();

        boolean markInRelation = DBConnection.excecuteUpdateOne(getTableName(), setValues, whereCondition);

        return markInRelation;
    }
}
