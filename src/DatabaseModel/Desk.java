package DatabaseModel;

import Utility.ExceptionHandler;

public class Desk {
    private int deskId;
    private String deskName;
    private boolean reserved;

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

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        try {
            setDeskNameHandler(deskName);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setDeskNameHandler(String deskName) throws Exception{
        if(deskName != null && !deskName.isEmpty()) {
            this.deskName = deskName;
        }
        else {
            throw new Exception("Desk Name is invalid");
        }
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
    
}
