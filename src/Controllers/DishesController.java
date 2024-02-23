package Controllers;

import java.util.ArrayList;
import java.util.Collections;

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

    public static boolean isValidDish(int dishId) throws Exception {
        Dish temp = new Dish();
        temp.setDishId(dishId);
        return dishes.contains(temp);
    }

    public static boolean addNewDish(String dishName, int price, int typeId) throws Exception {
        Dish dish = new Dish();

        dish.setDishId(Collections.max(dishes).getDishId() + 1);
        dish.setDishName(dishName);
        dish.setPrice(price);
        dish.setTypeId(typeId);

        boolean result = dishesRelation.addDishToRelation(dish);
        return result;
    }

    public static boolean modifyDish(int dishId, String dishName, int price, int type) throws Exception {

        boolean result = false;

        if (dishName != null) {
            if (!dishName.equals("")) {
                result = dishesRelation.updateDishInRelation(dishId, dishName, price, type);
            } else {
                throw new Exception("Dish name cannot be Empty :(");
            }
        }

        if (price != -1) {
            if(price >= 0) {
                result = dishesRelation.updateDishInRelation(dishId, dishName, price, type);
            } else {
                throw new Exception("Price must be greater than zero ");
            }
        }

        if (type != -1) {
            if(type >= 1 && type <= 5) {
                result = dishesRelation.updateDishInRelation(dishId, dishName, price, type);
            } else {
                throw new Exception("Type must be between 1 and 5 :( ");
            }
        }

        Dish temp = new Dish();
        temp.setDishId(dishId);
        int dishIndex = dishes.indexOf(temp);

        if (result) {
            Dish currentDish = dishes.get(dishIndex);
            if (dishName != null) {
                currentDish.setDishName(dishName);
            }

            if (price != -1) {
                currentDish.setPrice(price);
            }

            if (type != -1) {
                currentDish.setTypeId(type);
            }

            return true;
        }

        return result;
    }

    public static boolean removeDish(int dishId) throws Exception {
        boolean result = dishesRelation.removeDishInRelation(dishId);

        if (result) {
            Dish temp = new Dish();
            temp.setDishId(dishId);
            int dishIndex = dishes.indexOf(temp);
            dishes.remove(dishIndex);
        }

        return result;
    }
}
