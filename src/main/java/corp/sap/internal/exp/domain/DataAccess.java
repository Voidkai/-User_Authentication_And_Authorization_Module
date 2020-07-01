package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class DataAccess implements Serializable {
    private static final long serialVersionUID = -3405368204674664703L;
    private Integer id;
    private Integer dataCode;
    private Integer eid;
    private Integer uid;

    public DataAccess() {
    }

    public DataAccess(Integer id, Integer dataCode, Integer eid, Integer uid) {
        this.id = id;
        this.dataCode = dataCode;
        this.eid = eid;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataCode() {
        return dataCode;
    }

    public void setDataCode(Integer dataCode) {
        this.dataCode = dataCode;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
