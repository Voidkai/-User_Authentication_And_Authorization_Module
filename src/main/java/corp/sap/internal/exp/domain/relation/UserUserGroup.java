package corp.sap.internal.exp.domain.relation;

import java.io.Serializable;

public class UserUserGroup implements Serializable {

    private static final long serialVersionUID = 4541443490857400691L;
    private Integer id;
    private Integer userId;
    private Integer groupId;


    public UserUserGroup() {
    }

    public UserUserGroup(Integer id, Integer userId, Integer groupId) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
