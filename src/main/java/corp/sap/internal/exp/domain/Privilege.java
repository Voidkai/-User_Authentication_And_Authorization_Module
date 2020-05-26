package corp.sap.internal.exp.domain;

public class Privilege {

    private int privilege_id;
    private String privilege_code;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrivilege_code() {
        return privilege_code;
    }

    public void setPrivilege_code(String privilege_code) {
        this.privilege_code = privilege_code;
    }

    public int getPrivilege_id() {
        return privilege_id;
    }

    public void setPrivilege_id(int privilege_id) {
        this.privilege_id = privilege_id;
    }
}
