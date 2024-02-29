package DatabaseModel;

public class Order {

    public static final String seperator = "&";
    
    private int deskId;
    private int dishId;
    private int quantity;
    private boolean served;

    public Order() {
        // For Validating Orders
    }

    public Order(int deskId, int dishId, int quantity, boolean served) {
        this.deskId = deskId;
        this.dishId = dishId;
        this.quantity = quantity;
        this.served = served;
    }

    public int getDeskId() {
        return deskId;
    }

    public void setDeskId(int deskId) throws Exception {
        if (deskId >= 1) {
            this.deskId = deskId;
        } else {
            throw new Exception("Desk Id is invalid");
        }
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) throws Exception {
        if (dishId >= 1) {
            this.dishId = dishId;
        } else {
            throw new Exception("Dish Id is invalid");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws Exception {
        if (quantity >= 1) {
            this.quantity = quantity;
        } else {
            throw new Exception("Quantity is invalid");
        }
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + deskId;
        result = prime * result + dishId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (deskId != other.deskId)
            return false;
        if (dishId != other.dishId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.deskId + seperator + this.dishId + seperator + this.quantity;
    }
}
