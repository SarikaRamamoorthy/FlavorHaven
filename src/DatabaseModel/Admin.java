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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adminUserName == null) ? 0 : adminUserName.hashCode());
        result = prime * result + ((adminPassword == null) ? 0 : adminPassword.hashCode());
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
        Admin other = (Admin) obj;
        if (adminUserName == null) {
            if (other.adminUserName != null)
                return false;
        } else if (!adminUserName.equals(other.adminUserName))
            return false;
        if (adminPassword == null) {
            if (other.adminPassword != null)
                return false;
        } else if (!adminPassword.equals(other.adminPassword))
            return false;
        return true;
    }
}
