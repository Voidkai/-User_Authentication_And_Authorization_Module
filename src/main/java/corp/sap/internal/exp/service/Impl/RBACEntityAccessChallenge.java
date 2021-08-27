package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.service.EntityAccessChallenge;

import java.io.Serializable;

public class RBACEntityAccessChallenge implements EntityAccessChallenge, Serializable {

    private static final long serialVersionUID = -3316100378644803042L;
    private String entityCode;
    private Integer userId;
    private Integer entityId;

    public RBACEntityAccessChallenge() {
    }

    public RBACEntityAccessChallenge(String entityCode, Integer userId, Integer entityId) {
        this.entityCode = entityCode;
        this.userId = userId;
        this.entityId = entityId;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}

