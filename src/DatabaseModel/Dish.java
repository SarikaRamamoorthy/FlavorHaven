package DatabaseModel;

public class Dish implements Comparable<Dish>{
    // to seperate dish parameters
    public static final String seperator = "&";

    private int dishId;
    private String dishName;
    private int price;
    private int typeId;

    public Dish() {
        // Declared for adding new dishes
    }

    public Dish(int dishId, String dishName, int price, int typeId) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.price = price;
        this.typeId = typeId;
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

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) throws Exception {
        if (dishName != null && !dishName.equals("")) {
            this.dishName = dishName;
        } else {
            throw new Exception("Dish Name is invalid");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws Exception {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new Exception("Price is invalid");
        }
    }

    public int getDishTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) throws Exception {
        if (typeId >= 1 && typeId <= 5) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Dish other = (Dish) obj;
        if (dishId != other.dishId)
            return false;
        return true;
    }

    @Override
    public int compareTo(Dish o) {
        if(this.dishId < o.dishId) {
            return -1;
        }
        return 1;
    }
}
