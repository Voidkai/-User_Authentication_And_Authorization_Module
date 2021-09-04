package corp.sap.internal.exp.domain.relation;

import java.io.Serializable;

public class RolePrivilege implements Serializable {
    private static final long serialVersionUID = 4798346139293883510L;
    private Integer id;
    private Integer roleId;
    private Integer privilegeId;

    public RolePrivilege() {
    }

    public RolePrivilege(Integer id, Integer roleId, Integer privilegeId) {
        this.id = id;
        this.roleId = roleId;
        this.privilegeId = privilegeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}
