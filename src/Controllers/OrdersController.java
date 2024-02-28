package Controllers;

import java.util.ArrayList;
import java.util.List;

import DatabaseAccessObjects.DishesRelation;
import DatabaseAccessObjects.OrdersRelation;
import DatabaseModel.Dish;
import DatabaseModel.Order;
import Views.CustomerView;

public class OrdersController {
    private static OrdersRelation ordersRelation;
    private static DishesRelation dishesRelation;
    private static ArrayList<Order> orders;
    private static ArrayList<Dish> dishes;

    static {
        dishesRelation = DishesRelation.getInstance();
        ordersRelation = OrdersRelation.getInstance();
        orders = ordersRelation.getOrders();
        dishes = dishesRelation.getDishes();
    }

    public static void reinitialize() {
        ordersRelation.reinitialize();
    }

    public static Table returnCurrentOrders() {

        int currentDeskId = CustomerView.CURRENT_DESK_ID;

        List<String> columns = List.of("Dish Name", "Quantity", "Price", "Order Status");

        String[] headers = columns.toArray(new String[columns.size()]);
        String[][] data = new String[orders.size()][];
        int dataIndex = 0;

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if(order.getDeskId() == currentDeskId) {
                Dish dish = new Dish();
                try {
                    String[] orderRow = new String[4];
                    
                    dish.setDishId(order.getDishId());
                    orderRow[0] = dishes.get(dishes.indexOf(dish)).getDishName();
                    orderRow[1] = order.getQuantity() + "";
                    orderRow[2] = (dishes.get(dishes.indexOf(dish)).getPrice() * order.getQuantity()) + "";
                    orderRow[3] = order.isServed() ? "Order Finished" : "Preparing your order";
    
                    data[dataIndex++] = orderRow;
                    
                } catch (Exception e) {
                    return new Table(headers, new String[orders.size()][]);
                }
            }
        }

        return new Table(headers, data);
    }
    
    public static boolean addOrder(int deskId, int dishId, int quantity) throws Exception {
        Order order = new Order();
        boolean result = false;

        order.setDeskId(deskId);
        order.setDishId(dishId);
        order.setQuantity(quantity);
        order.setServed(false);
        
        result = ordersRelation.addOrderToRelation(order);

        return result;
    }
    
    public static int wasPreviouslyOrdered(int deskId, int dishId, int quantity, boolean served) throws Exception{
        Order order = new Order();
    
        order.setDeskId(deskId);
        order.setDishId(dishId);
        
        int orderIndex = orders.indexOf(order);

        if(orderIndex == -1) {
            return orderIndex;
        }

        else {
            Order oldOrder = orders.get(orderIndex);
            if(oldOrder.isServed()) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public static boolean updateOrder(int deskId, int dishId, int quanity, boolean served) throws Exception {
        Order order = new Order();

        order.setDeskId(deskId);
        order.setDishId(dishId);
        order.setServed(served);

        Order oldOrder = orders.get(orders.indexOf(order));
        order.setQuantity(oldOrder.getQuantity() + quanity);
        
        boolean result = ordersRelation.updateOrderToRelation(order);

        if(result) {
            oldOrder.setQuantity(oldOrder.getQuantity() + quanity);
            oldOrder.setServed(served);
        }

        return result;
    }

}
