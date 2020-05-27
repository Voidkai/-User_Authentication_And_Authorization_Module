package corp.sap.internal.exp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceTicket implements Serializable {
    private static final long serialVersionUID = -8277734413593886973L;
    private int id;
    private Timestamp create_time;
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
