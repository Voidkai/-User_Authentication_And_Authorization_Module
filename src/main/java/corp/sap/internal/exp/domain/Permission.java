package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class Permission implements Serializable {

    private static final long serialVersionUID = -3405368204674664773L;

    private Integer id;
    private String code;
    private String description;

    public Permission() {
    }

    public Permission(Integer id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
