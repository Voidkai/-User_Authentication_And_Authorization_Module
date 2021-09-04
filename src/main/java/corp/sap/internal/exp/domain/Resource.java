package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class Resource implements Serializable {

    private static final long serialVersionUID = 1247525667210531424L;
    private Integer id;
    private String name;
    private Integer code;

    public Resource() {
    }

    public Resource(Integer id, String name, Integer code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
