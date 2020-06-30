package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.service.DataAccessChallenge;

public class RBACDataAccessChallenge implements DataAccessChallenge {
    private Integer userId;
    private Integer dataId;

    public RBACDataAccessChallenge() {
    }

    public RBACDataAccessChallenge(Integer userId, Integer dataId) {
        this.userId = userId;
        this.dataId = dataId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }
}
