package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DatabaseModel.Dish;
import Utility.ExceptionHandler;

public class DishesRelation extends Relation {

    private static DishesRelation dishesRelation;
    private static ArrayList<Dish> dishes;

    public static DishesRelation getInstance() {
        if(dishesRelation == null) {
            dishesRelation = new DishesRelation();
        }
        return dishesRelation;
    }

    private DishesRelation() {
        dishes = new ArrayList<>();
        setTableName("dishes");
        ArrayList<String> columnNames = getColumnNames(getTableName());
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < columnNames.size(); i++) {
            map.put(i, columnNames.get(i));
        }
        setTableAttributes(map);

        initializeDishes();
    }

    private void initializeDishes() {
        try {
            ResultSet results = DBConnection.excecuteSelect("*", getTableName(), null);
            while (results.next()) {
                Dish dish = new Dish(results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4));

                dishes.add(dish);
            }
        } catch (Exception e) {
            ExceptionHandler.emptyTableException(getTableName());
        }
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public boolean addDishToRelation(Dish dish) {
        String values = dish.getDishId() + "," + "'" + dish.getDishName() + "'," + dish.getPrice() + "," + dish.getDishTypeId();
        boolean result = DBConnection.excecuteInsertOne(getTableName(), values);
        if(result){
            dishes.add(dish);
        }
        return result;
    }
}
