package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.service.PermissionChallenge;

public class RBACPermissionChallenge implements PermissionChallenge {
    private Integer id;
    private String role;
    private String privilegeCode;
    private Integer userId;

    public RBACPermissionChallenge() {
    }

    public RBACPermissionChallenge(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }


    public RBACPermissionChallenge(Integer id, String privilegeCode) {
        this.id = id;
        this.privilegeCode = privilegeCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }
}
