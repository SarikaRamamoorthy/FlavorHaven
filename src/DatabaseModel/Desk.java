package DatabaseModel;

public class Desk {

    public static String seperator = "&";
    private int deskId;
    private String deskName;
    private int seatCount;
    private boolean reserved;

    public int getDeskId() {
        return deskId;
    }

    public Desk(int deskId, String deskName, boolean reserved) {
        this.deskId = deskId;
        this.deskName = deskName;
        this.seatCount = seatCount;
        this.reserved = reserved;
    }

    // TODO: getter setter methods for desk

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

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return this.deskId + seperator + this.deskName + seperator + this.reserved;
    }
}
