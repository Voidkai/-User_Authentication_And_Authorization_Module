package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.service.PrivilegeChallenge;

public class RBACPrivilegeChallenge implements PrivilegeChallenge {

    private Integer id;
    private String role;
    private String privilegeCode;
    private Integer userId;

    public RBACPrivilegeChallenge() {
    }

    public RBACPrivilegeChallenge(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }


    public RBACPrivilegeChallenge(Integer userId, String privilegeCode) {
        this.userId = userId;
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
