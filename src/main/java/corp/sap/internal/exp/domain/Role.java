package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1570489653361552972L;
    private int roleId;
    private String name;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
