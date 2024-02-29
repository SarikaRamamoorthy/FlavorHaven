package DatabaseModel;

public class Desk {

    public static String seperator = "&";
    private int deskId;
    private String deskName;
    private int seatCount;
    private boolean reserved;
    private int orderAmount;

    public Desk() {
        // For validating desks
    }
    
    public Desk(int deskId, String deskName, int seatCount, boolean reserved, int orderAmount) {
        this.deskId = deskId;
        this.deskName = deskName;
        this.seatCount = seatCount;
        this.reserved = reserved;
        this.orderAmount = orderAmount;
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

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) throws Exception {
        if (deskName != null && !deskName.isEmpty()) {
            this.deskName = deskName;
        } else {
            throw new Exception("Desk Name is invalid");
        }
    }

    public int getSeatCount() {
        return this.seatCount;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) throws Exception{
        if(orderAmount >= 0) {
            this.orderAmount = orderAmount;
        }
        else {
            throw new Exception(" Order Amount Cannot be negative ");
        }
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + deskId;
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
        Desk other = (Desk) obj;
        if (deskId != other.deskId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.deskId + seperator + this.deskName + seperator + this.seatCount + seperator + this.reserved + seperator + this.orderAmount;
    }
}
