package DatabaseAccessObjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DatabaseModel.Dish;
import Utility.ExceptionHandler;

public class DishesRelation extends Relation {

    private static ArrayList<Dish> dishes = null;

    static {
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

    private static void initializeDishes() {
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

    public static ArrayList<Dish> getDishes() {
        return dishes;
    }
}
