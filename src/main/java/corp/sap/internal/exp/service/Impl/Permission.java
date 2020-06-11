package corp.sap.internal.exp.service.Impl;

public class Permission {
    private String entity;
    private Integer id;
    private String role;
    private String operation;
    private String privilegeCode;

    public Permission() {
    }

    public Permission(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public Permission(String entity, String operation) {
        this.entity = entity;
        this.operation = operation;
    }

    public Permission(String entity, Integer id, String privilegeCode) {
        this.entity = entity;
        this.id = id;
        this.privilegeCode = privilegeCode;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }
}
