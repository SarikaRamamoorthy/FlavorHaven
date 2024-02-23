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
        if (dishesRelation == null) {
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
        String values = dish.getDishId() + "," + "'" + dish.getDishName() + "'," + dish.getPrice() + ","
                + dish.getDishTypeId();
        boolean result = DBConnection.excecuteInsertOne(getTableName(), values);
        if (result) {
            dishes.add(dish);
        }
        return result;
    }

    public boolean updateDishInRelation(int dishId, String dishName, int price, int type) {
        HashMap<Integer, String> attributes = getTableAttributes();
        String whereCondition = attributes.get(0)+" = "+dishId;
        String setValues = "";

        if(dishName != null) {
            setValues += attributes.get(1)+" = '" +dishName+ "'";
        }

        if(price != -1) {
            setValues += attributes.get(2)+" = "+price;
        }

        if(type != -1) {
            setValues += attributes.get(3)+" = "+type;
        }

        boolean result = DBConnection.excecuteUpdateOne(getTableName(), setValues, whereCondition);

        return result;
    }

    public boolean removeDishInRelation(int dishId) {
        HashMap<Integer, String> attributes = getTableAttributes();
        String whereCondition = attributes.get(0)+" = "+dishId;

        boolean result = DBConnection.excecuteRemoveOne(getTableName(), whereCondition);

        return result;
    }
}
