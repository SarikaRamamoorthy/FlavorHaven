package DatabaseModel;

import Utility.ExceptionHandler;

public class Variety {
    private int typeId;
    private String typeName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        try {
            setTypeIdHandler(typeId);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setTypeIdHandler(int typeId) throws Exception{
        if(typeId >= 1) {
            this.typeId = typeId;       
        }
        else {
            throw new Exception("Type Id is invalid");
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        try {
            setTypeNameHandler(typeName);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setTypeNameHandler(String typeName) throws Exception{
        if(typeName != null && !typeName.isEmpty()) {
            this.typeName = typeName;
        }
        else {
            throw new Exception("Type Name is invalid");
        }
    }
}
