package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1570489653361552972L;
    private Integer id;
    private String name;
    private Integer baseRole;
    private String description;

    public Role(){}

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Integer baseRole, String description) {
        this.name = name;
        this.baseRole = baseRole;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseRole() {
        return baseRole;
    }

    public void setBaseRole(Integer baseRole) {
        this.baseRole = baseRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
