package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Privilege;
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
public class PrivilegeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Privilege> getPrivByRoleId(Integer roleId){
        String sql = "select * FROM privilege_role WHERE role_id = "+roleId+" group by privilege_id";
        List<Privilege> list = jdbcTemplate.query(sql,new PrivilegeRoleRowMapper());

        return list;
    }
    public List<Privilege> getPrivByPrivId(Integer privId){
        String sql = "select * FROM privileges WHERE privilege_id = "+privId;
        List<Privilege> list = jdbcTemplate.query(sql, new PrivilegeRowMapper());

        return list;
    }

}

class PrivilegeRowMapper implements RowMapper<Privilege>{
    @Override
    public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Privilege(resultSet.getInt("privilege_id"),resultSet.getString("privilege_code"),resultSet.getString("description"));
    }
}

class PrivilegeRoleRowMapper implements RowMapper<Privilege>{
    @Override
    public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
        Privilege privilege = new Privilege();
        privilege.setPrivilegeId(resultSet.getInt("privilege_id"));
        return privilege;
    }
}
