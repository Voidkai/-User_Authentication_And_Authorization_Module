package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PrivilegeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> getprivByUser(int id){
        String sql = "SELECT DISTINCT p.* FROM users AS u " +
                "LEFT JOIN role_user AS ur ON    u.user_id = ur.user_id " +
                "LEFT JOIN roles AS r ON    r.role_id = ur.role_id " +
                "LEFT JOIN privilege_role AS rp ON    r.role_id = rp.role_id " +
                "LEFT JOIN privileges AS p ON    p.privilege_id = rp.privilege_id " +
                "WHERE    u.user_id = " + id;

        List list = jdbcTemplate.queryForList(sql);

        return list;
    }

}
