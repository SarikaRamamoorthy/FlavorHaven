package Controllers;

import java.util.ArrayList;
import java.util.Arrays;

import DatabaseAccessObjects.DishesRelation;
import DatabaseModel.Dish;

public class DishesController {

    private static ArrayList<Dish> dishes = DishesRelation.getDishes();

    public static Table returnAllDishes() {
        ArrayList<String> columns = new ArrayList<>(DishesRelation.getTableAttributes().values());

        String[] headers = columns.toArray(new String[columns.size()]);

        String[][] data = new String[dishes.size()][];

        for (int i = 0; i < dishes.size(); i++) {
            String[] dishRow = dishes.get(i).toString().split(Dish.seperator);
            data[i] = dishRow;
        }

        Table table = new Table(headers, data);
        return table;
    }
}
