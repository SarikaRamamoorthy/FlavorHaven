package DatabaseModel;

import Utility.ExceptionHandler;

public class Admin {
    private int adminId;
    private String adminUserName;
    private String adminPassword;

    public Admin(int adminid, String adminUserName, String adminPassword) {
        setAdminId(adminid);
        setAdminUserName(adminUserName);
        setAdminPassword(adminPassword);
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        try {
            setAdminIdHandler(adminId);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setAdminIdHandler(int adminId) throws Exception {
        if(adminId >= 1) {
            this.adminId = adminId;
        }
        else {
            throw new Exception("Admin Id is invalid");
        }
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        try {
            setAdminUserNameHandler(adminUserName);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setAdminUserNameHandler (String adminUserName) throws Exception  {
        if((adminUserName != null) && (!adminUserName.isEmpty())) {
            this.adminUserName = adminUserName;
        }
        else {
            throw new Exception("User Name is invalid");
        }
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        try {
            setAdminPasswordHandler(adminPassword);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }
    }

    private void setAdminPasswordHandler(String adminPassword) throws Exception{
        if(adminPassword != null && !adminPassword.isEmpty()) {
            this.adminPassword = adminPassword;
        }
        else {
            throw new Exception("Admin password invalid");
        }
    }

    @Override
    public boolean equals(Object obj) {
        Admin admin = (Admin) obj;
        return this.getAdminUserName().equals(admin.getAdminUserName()) && this.getAdminPassword().equals(admin.getAdminPassword());
    }
}
