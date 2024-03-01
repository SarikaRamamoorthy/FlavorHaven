package Controllers;

import java.util.ArrayList;
import java.util.List;

import DatabaseAccessObjects.DishesRelation;
import DatabaseAccessObjects.OrdersRelation;
import DatabaseModel.Desk;
import DatabaseModel.Dish;
import DatabaseModel.Order;
import Utility.ExceptionHandler;

public class OrdersController {
    private static OrdersRelation ordersRelation;
    private static DishesRelation dishesRelation;
    private static final ArrayList<Order> orders;
    private static final ArrayList<Dish> dishes;

    static {
        dishesRelation = DishesRelation.getInstance();
        ordersRelation = OrdersRelation.getInstance();
        orders = ordersRelation.getOrders();
        dishes = dishesRelation.getDishes();
    }

    public static Table returnCurrentOrders(int deskId) {

        List<String> columns = List.of("Dish Name", "Quantity", "Price", "Order Status");

        String[] headers = columns.toArray(new String[columns.size()]);
        ArrayList<String[]> data = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if(order.getDeskId() == deskId) {
                Dish dish = new Dish();
                try {
                    String[] orderRow = new String[4];
                    
                    dish.setDishId(order.getDishId());
                    orderRow[0] = dishes.get(dishes.indexOf(dish)).getDishName();
                    orderRow[1] = order.getQuantity() + "";
                    orderRow[2] = (dishes.get(dishes.indexOf(dish)).getPrice() * order.getQuantity()) + "";
                    orderRow[3] = order.isServed() ? "Order Finished" : "Preparing your order";
    
                    data.add(orderRow);
                    
                } catch (Exception e) {
                    return new Table(headers, new String[orders.size()][]);
                }
            }
        }

        return new Table(headers, data.toArray(new String[data.size()][]));
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

    public static boolean isOrderFinished(int deskId) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if(order.getDeskId() == deskId && !order.isServed()) {
                return false;
            }
        }
        return true;
    }

    public static Table returnBill(int deskId) {

        List<String> columns = List.of("Dish Name", "Quantity", "Price", "Net Price");

        String[] headers = columns.toArray(new String[columns.size()]);
        ArrayList<String[]> data = new ArrayList<>();

        int total = 0;

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if(order.getDeskId() == deskId) {
                Dish dish = new Dish();
                try {
                    String[] orderRow = new String[4];
                    
                    dish.setDishId(order.getDishId());
                    Dish currentDish = dishes.get(dishes.indexOf(dish));

                    int netPrice = (currentDish.getPrice() * order.getQuantity());
                    orderRow[0] = currentDish.getDishName();
                    orderRow[1] = order.getQuantity() + "";
                    orderRow[2] = dish.getPrice() + "";
                    orderRow[3] = netPrice + "";
                    
                    data.add(orderRow);
                    total += netPrice;
                    
                } catch (Exception e) {
                    return new Table(headers, new String[orders.size()][]);
                }
            }
        }

        String[] orderRow = new String[4];

        orderRow[0] = "-";
        orderRow[1] = "-";
        orderRow[2] = "Total";
        orderRow[3] = total + "";

        data.add(orderRow);

        return new Table(headers, data.toArray(new String[data.size()][]));    
    }

    public static int calculateBill(int deskId) {
        int total = 0;

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if(order.getDeskId() == deskId) {
                Dish dish = new Dish();
                try {
                    dish.setDishId(order.getDishId());
                    Dish currentDish = dishes.get(dishes.indexOf(dish));
                    
                    total += currentDish.getPrice() * order.getQuantity();
                } catch (Exception e) {
                    ExceptionHandler.specialExceptions(e.getMessage());
                }
            }
        }

        return total;
    }

    public static void removeOrdersInDesk(int deskId) {
        Desk desk = new Desk();
        try {
            desk.setDeskId(deskId);
            
            boolean result = ordersRelation.removeOrdersInDeskInRelation(desk);

            if(result) {
                orders.removeIf(order -> (order.getDeskId() == deskId));
            }
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }

    }

    public static Table returnAllUnservedOrders() {
        ArrayList<String> columns = new ArrayList<>(ordersRelation.getTableAttributes().values());
        columns.add(0, "Order Id");
        columns.remove(columns.size() - 1);

        String[] headers = columns.toArray(new String[columns.size()]);
        ArrayList<String[]> data = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if(!order.isServed()) {
                String[] orderRow = new String[4];
                String[] orderString = order.toString().split(Order.seperator);
                
                orderRow[0] = (i+1) + "";
                orderRow[1] = orderString[0];
                orderRow[2] = orderString[1];
                orderRow[3] = orderString[2];

                data.add(orderRow);
            }
        }

        return new Table(headers, data.toArray(new String[data.size()][]));
    }

    public static boolean isValidOrder(int orderId) {
        return orderId >= 0 && orderId < orders.size();
    }

    public static boolean isServed(int orderId) {
        return orders.get(orderId).isServed();
    }

    public static boolean markAsServed(int orderId) {
        Order order = orders.get(orderId);
        boolean marked = ordersRelation.markOrderAsServedInRelation(order);

        if(marked) {
            order.setServed(true);
        }
        return marked;
    }
}
