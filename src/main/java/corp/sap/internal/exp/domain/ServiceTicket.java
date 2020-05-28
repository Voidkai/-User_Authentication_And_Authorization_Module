package corp.sap.internal.exp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceTicket implements Serializable {
    private static final long serialVersionUID = -8277734413593886973L;
    private int id;
    private Timestamp updateTime;
    private int userId;
    private String content;

    public ServiceTicket(int id, Timestamp updateTime, int userId, String content) {
        this.id = id;
        this.updateTime = updateTime;
        this.userId = userId;
        this.content = content;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
