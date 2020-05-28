package corp.sap.internal.exp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceTicket implements Serializable {
    private static final long serialVersionUID = -8277734413593886973L;
    private int id;
    private Timestamp update_time;
    private int user_id;
    private String content;

    public ServiceTicket(int id, Timestamp update_time, int user_id, String content) {
        this.id = id;
        this.update_time = update_time;
        this.user_id = user_id;
        this.content = content;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
