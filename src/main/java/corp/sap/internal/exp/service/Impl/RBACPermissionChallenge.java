package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.service.PermissionChallenge;

public class RBACPermissionChallenge implements PermissionChallenge {
    private String entity;
    private Integer id;
    private String role;
    private String privilegeCode;
    private Integer userId;

    public RBACPermissionChallenge() {
    }

    public RBACPermissionChallenge(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public RBACPermissionChallenge(String entity, String privilegeCode) {
        this.entity = entity;
        this.privilegeCode = privilegeCode;
    }

    public RBACPermissionChallenge(String entity, Integer id, String privilegeCode) {
        this.entity = entity;
        this.id = id;
        this.privilegeCode = privilegeCode;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
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
