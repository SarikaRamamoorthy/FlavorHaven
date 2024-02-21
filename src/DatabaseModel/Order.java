package DatabaseModel;

import Utility.ExceptionHandler;

public class Order {
    private int deskId;
    private int dishId;
    private int quantity;
    private boolean served;

    public int getDeskId() {
        return deskId;
    }

    public void setDeskId(int deskId) {
        try {
            setDeskIdHandler(deskId);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setDeskIdHandler(int deskId) throws Exception{
        if(deskId >= 1) {
            this.deskId = deskId;       
        }
        else {
            throw new Exception("Desk Id is invalid");
        }
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

    private void setDishIdHandler(int dishId) throws Exception{
        if(dishId >= 1) {
            this.dishId = dishId;       
        }
        else {
            throw new Exception("Dish Id is invalid");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        try {
            setQuantityHandler(quantity);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setQuantityHandler(int quantity) throws Exception{
        if(quantity >= 1) {
            this.quantity = quantity;       
        }
        else {
            throw new Exception("Quantity is invalid");
        }
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

}
