package DatabaseModel;

public class Variety {
    private int typeId;
    private String typeName;

    public Variety(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) throws Exception {
        if (typeId >= 1) {
            this.typeId = typeId;
        } else {
            throw new Exception("Type Id is invalid");
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) throws Exception {
        if (typeName != null && !typeName.isEmpty()) {
            this.typeName = typeName;
        } else {
            throw new Exception("Type Name is invalid");
        }
    }
}
