package DatabaseModel;

import Utility.ExceptionHandler;

public class Dish {
    // to seperate dish parameters
    public static final String seperator = "&";

    private int dishId;
    private String dishName;
    private int price;
    private int typeId;

    public Dish(int dishId, String dishName, int price, int typeId) {
        setDishId(dishId);
        setDishName(dishName);
        setPrice(price);
        setTypeId(typeId);
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        try {
            setDishIdHandler(dishId);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setDishIdHandler(int dishId) throws Exception {
        if (dishId >= 1) {
            this.dishId = dishId;
        } else {
            throw new Exception("Dish Id is invalid");
        }
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        try {
            setDishNameHandler(dishName);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setDishNameHandler(String dishName) throws Exception {
        if (dishName != null && !dishName.isEmpty()) {
            this.dishName = dishName;
        } else {
            throw new Exception("Dish Name is invalid");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        try {
            setPriceHandler(price);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setPriceHandler(int price) throws Exception {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new Exception("Price is invalid");
        }
    }

    public int getDishTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        try {
            setTypeIdHandler(typeId);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setTypeIdHandler(int typeId) throws Exception {
        if (typeId >= 1) {
            this.typeId = typeId;
        } else {
            throw new Exception("Type Id is invalid");
        }
    }

    @Override
    public String toString() {
        return this.dishId + seperator + this.dishName + seperator + this.price + seperator + this.typeId;
    }

    @Override
    public boolean equals(Object obj) {
        Dish dish = (Dish) obj;
        return this.dishId == dish.dishId;
    }
}
