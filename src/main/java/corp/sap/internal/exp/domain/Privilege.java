package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class Privilege implements Serializable {

    private int privilege_id;
    private String name;
    private String privilege_code;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
