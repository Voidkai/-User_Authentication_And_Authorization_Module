package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.service.DataAccessChallenge;

public class RBACDataAccessChallenge implements DataAccessChallenge {
    private String dataName;
    private Integer uid;
    private Integer eid;

    public RBACDataAccessChallenge() {
    }

    public RBACDataAccessChallenge(String dataName, Integer uid, Integer eid) {
        this.dataName = dataName;
        this.uid = uid;
        this.eid = eid;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}

