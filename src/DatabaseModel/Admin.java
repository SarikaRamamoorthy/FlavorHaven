package DatabaseModel;

public class Admin {
    private int adminId;
    private String adminUserName;
    private String adminPassword;

    public Admin() {
        // for validating new login admin credentials
    }

    public Admin(int adminid, String adminUserName, String adminPassword) {
        this.adminId = adminid;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) throws Exception {
        if (adminId >= 1) {
            this.adminId = adminId;
        } else {
            throw new Exception("Admin Id is invalid");
        }
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) throws Exception {
        if ((adminUserName != null) && (!adminUserName.isEmpty())) {
            this.adminUserName = adminUserName;
        } else {
            throw new Exception("User Name is invalid");
        }
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) throws Exception {
        if (adminPassword != null && !adminPassword.isEmpty()) {
            this.adminPassword = adminPassword;
        } else {
            throw new Exception("Admin password invalid");
        }
    }

    // a.equals(b);

    @Override
    public boolean equals(Object obj) {
        Admin admin = (Admin) obj;
        return this.adminUserName.equals(admin.adminUserName) && this.adminPassword.equals(admin.adminPassword);
    }
}
