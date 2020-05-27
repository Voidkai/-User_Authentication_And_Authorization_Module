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
                "LEFT JOIN role_user AS ur " +
                "ON" +
                "    u.user_id = ur.user_id\n" +
                "LEFT JOIN roles AS r\n" +
                "ON\n" +
                "    r.role_id = ur.role_id\n" +
                "LEFT JOIN privilege_role AS rp\n" +
                "ON\n" +
                "    r.role_id = rp.role_id\n" +
                "LEFT JOIN privileges AS p\n" +
                "ON\n" +
                "    p.privilege_id = rp.privilege_id\n" +
                "WHERE\n" +
                "    u.user_id = " + id;

        List list = jdbcTemplate.queryForList(sql);

        return list;
    }

}
