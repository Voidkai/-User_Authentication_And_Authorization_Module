package corp.sap.internal.exp.domain;

import java.io.Serializable;

public class EntityAccess implements Serializable {
    private static final long serialVersionUID = -950960556751864475L;
    private Integer id;
    private Integer entityCode;
    private Integer entityId;
    private Integer userId;

    public EntityAccess() {
    }

    public EntityAccess(Integer id, Integer entityCode, Integer entityId, Integer userId) {
        this.id = id;
        this.entityCode = entityCode;
        this.entityId = entityId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(Integer entityCode) {
        this.entityCode = entityCode;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
