package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.UserGroup;
import corp.sap.internal.exp.domain.relation.UserUserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserGroupDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer joinUserGroup(Integer userId, Integer groupId){
        String sql = "insert into relation_user_user_group values(null,"+userId+" , "+groupId+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select id from relation_user_user_group where user_id = "+userId, Integer.class);
    }

    public void removeUser(Integer userId, Integer groupId){
        String sql = "delete from relation_user_user_group where user_id = "+ userId+" and group_id = "+groupId;
        jdbcTemplate.update(sql);
    }

    public Integer createUserGroup(UserGroup userGroup){
        String sql = "insert into user_group (name, description) values ("+userGroup.getName()+", "+userGroup.getDescription()+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select id from user_group where name = "+userGroup.getName(), Integer.class);
    }
}
