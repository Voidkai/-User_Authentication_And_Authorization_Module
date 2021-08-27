package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Permission;
import corp.sap.internal.exp.domain.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PermissionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Permission> getPermByRoleId(Integer roleId){
        String sql = "select * FROM permission_role WHERE role_id = "+roleId+" group by permission_code";
        List<Permission> list = jdbcTemplate.query(sql,new PermissionRoleRowMapper());

        return list;
    }
    public List<Permission> getPermByPrivId(Integer permId){
        String sql = "select * FROM permissions WHERE id = "+permId;
        List<Permission> list = jdbcTemplate.query(sql, new PermissionRowMapper());

        return list;
    }

    public List<Permission> getPermByUserId(Integer userId){
        String sql = "select p.* from role_user ru join users u  on u.id =  ru.user_id\n" +
                "    join permission_role pr on ru.role_id = pr.role_id\n" +
                "    join permissions p on pr.permission_code = p.code\n" +
                "    where u.id="+userId;
        List<Permission> list = jdbcTemplate.query(sql, new PermissionRowMapper());

        return list;
    }

}

class PermissionRowMapper implements RowMapper<Permission>{
    @Override
    public Permission mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Permission(resultSet.getInt("id"),resultSet.getString("code"),resultSet.getString("description"));
    }
}

class PermissionRoleRowMapper implements RowMapper<Permission>{
    @Override
    public Permission mapRow(ResultSet resultSet, int i) throws SQLException {
        Permission permission = new Permission();
        permission.setId(resultSet.getInt("id"));
        return permission;
    }
}
