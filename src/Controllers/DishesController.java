package Controllers;

import java.util.ArrayList;

import DatabaseAccessObjects.DishesRelation;
import DatabaseAccessObjects.VarietyRelation;
import DatabaseModel.Dish;
import DatabaseModel.Variety;

public class DishesController {

    private static DishesRelation dishesRelation;
    private static VarietyRelation varietyRelation;
    private static ArrayList<Dish> dishes;
    private static ArrayList<Variety> varieties;

    static {
        dishesRelation = DishesRelation.getInstance();
        varietyRelation = VarietyRelation.getInstance();
        dishes = dishesRelation.getDishes();
        varieties = varietyRelation.getVarieties();
    }

    public static Table returnAllDishesWithoutJoin() {
        ArrayList<String> columns = new ArrayList<>(dishesRelation.getTableAttributes().values());

        String[] headers = columns.toArray(new String[columns.size()]);

        String[][] data = new String[dishes.size()][];

        for (int i = 0; i < dishes.size(); i++) {
            String[] dishRow = dishes.get(i).toString().split(Dish.seperator);
            data[i] = dishRow;
        }

        Table table = new Table(headers, data);
        return table;
    }

    public static Table returnAllDishes() {
        ArrayList<String> columns = new ArrayList<>(dishesRelation.getTableAttributes().values());
        String[] headers = columns.toArray(new String[columns.size()]);
        String[][] data = new String[dishes.size()][];

        headers[headers.length - 1] = "type_name";

        for (int i = 0; i < dishes.size(); i++) {
            String[] dishRow = dishes.get(i).toString().split(Dish.seperator);
            for (Variety variety : varieties) {
                if (dishRow[dishRow.length - 1].equals(variety.getTypeId() + "")) {
                    dishRow[dishRow.length - 1] = variety.getTypeName();
                }
            }
            data[i] = dishRow;
        }

        Table table = new Table(headers, data);
        return table;
    }
}
