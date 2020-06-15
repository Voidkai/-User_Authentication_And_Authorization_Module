package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class Privilege implements Serializable {

    private static final long serialVersionUID = -3405368204674664773L;

    private Integer privilegeId;
    private String privilegeCode;
    private String description;

    public Privilege(){

    }

    public Privilege(Integer privilegeId, String privilegeCode, String description) {
        this.privilegeId = privilegeId;
        this.privilegeCode = privilegeCode;
        this.description = description;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
