package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1570489653361552972L;
    private Integer roleId;
    private String name;

    public Role(){}

    public Role(Integer roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
