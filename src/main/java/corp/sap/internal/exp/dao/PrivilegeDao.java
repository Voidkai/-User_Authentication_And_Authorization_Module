package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PrivilegeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer createPrivilege(Privilege privilege){
        String sql = "insert into privilege (name, resource_id, operation) values ("+privilege.getName()+", "+privilege.getResourceId()+", "+ privilege.getOperation()+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select id from privilege where resource_id ="+privilege.getResourceId(),Integer.class);
    }

    public void deletePrivilege(Integer privilegeId){
        String sql = "delete from privilege where id = "+privilegeId;
        jdbcTemplate.update(sql);
    }

    public Privilege findPrivilegeById(Integer privilegeId){
        String sql = "select * from privilege where id = "+ privilegeId;

        return jdbcTemplate.queryForObject(sql, new RowMapper<Privilege>() {
            @Override
            public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
                Privilege privilege = new Privilege();
                privilege.setId(resultSet.getInt("id"));
                privilege.setName(resultSet.getString("name"));
                privilege.setResourceId(resultSet.getInt("resource_id"));
                privilege.setOperation(resultSet.getString("operation"));

                return privilege;
            }
        });
    }

    public List<Privilege> findChildPrivilegeById(Integer privilegeId){
        String sql = "select * from privilege where parent_id = "+privilegeId;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Privilege privilege = new Privilege();
            privilege.setId(resultSet.getInt("id"));
            privilege.setName(resultSet.getString("name"));
            privilege.setResourceId(resultSet.getInt("resource_id"));
            privilege.setOperation(resultSet.getString("operation"));

            return privilege;
        });
    }

    public Privilege findParentPrivilegeById(Integer privilegeId){
        String sql = "select parent_id from privilege where id ="+privilegeId;
        Integer parentId = jdbcTemplate.queryForObject(sql, Integer.class);
        if(parentId == null) return new Privilege();
        sql = "select * from privilege where id = "+ parentId;
        return jdbcTemplate.queryForObject(sql, new RowMapper<Privilege>() {
            @Override
            public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
                Privilege privilege = new Privilege();
                privilege.setId(resultSet.getInt("id"));
                privilege.setName(resultSet.getString("name"));
                privilege.setResourceId(resultSet.getInt("resource_id"));
                privilege.setOperation(resultSet.getString("operation"));

                return privilege;
            }
        });
    }

    public List<Privilege> getPrivByRoleId(Integer roleId){
        String sql = "select * FROM relation_role_privilege WHERE role_id = "+roleId+" group by permission_code";
        List<Privilege> list = jdbcTemplate.query(sql,new PrivilegeRoleRowMapper());

        return list;
    }
    public List<Privilege> getPrivByPrivId(Integer permId){
        String sql = "select * FROM privilege WHERE id = "+permId;
        List<Privilege> list = jdbcTemplate.query(sql, new PrivilegeRowMapper());

        return list;
    }

    public List<Privilege> getPrivByUserId(Integer userId){
        String sql = "select p.* from relation_user_role ru join user u  on u.id =  ru.user_id\n" +
                "    join relation_role_privilege pr on ru.role_id = pr.role_id\n" +
                "    join privilege p on pr.privilege_id = p.id\n" +
                "    where u.id="+userId;
        List<Privilege> list = jdbcTemplate.query(sql, new PrivilegeRowMapper());

        return list;
    }

}

class PrivilegeRowMapper implements RowMapper<Privilege>{
    @Override
    public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Privilege(resultSet.getInt("id"),resultSet.getString("code"),resultSet.getString("description"));
    }
}

class PrivilegeRoleRowMapper implements RowMapper<Privilege>{
    @Override
    public Privilege mapRow(ResultSet resultSet, int i) throws SQLException {
        Privilege privilege = new Privilege();
        privilege.setId(resultSet.getInt("id"));
        return privilege;
    }
}
