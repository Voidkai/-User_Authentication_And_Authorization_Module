package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Role;
import corp.sap.internal.exp.domain.relation.RolePrivilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer createRole(Role role){
        String sql = "insert into role values ( null,"+ role.getName()+",null,null)";
        jdbcTemplate.update(sql);

        return jdbcTemplate.queryForObject("select id from role where name = "+role.getName(), Integer.class);
    }

    public void deleteRole(Role role){
        String sql = "delete from role where id = "+role.getId();
        jdbcTemplate.update(sql);
    }

    public Role updateRole(Role role){
        String sql = "update role set name = "+role.getName()+" where id ="+role.getId();
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select * from role where id =" + role.getId(), new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                return role;
            }
        });
    }

    public Role findRoleById(Integer id){
        String sql = "select * from role where id ="+id;
        return jdbcTemplate.queryForObject(sql, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setBaseRole(resultSet.getInt("base_role"));
                role.setDescription(resultSet.getString("description"));
                return role;
            }
        });
    }

    public RolePrivilege assignPrivilege(Integer roleId, Integer privilegeId){
        String sql = "insert into relation_role_privilege values ( null, "+ roleId+", "+privilegeId+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select * from relation_role_privilege where role_id = " + roleId + " and privilege_id =" + privilegeId, new RowMapper<RolePrivilege>() {
            @Override
            public RolePrivilege mapRow(ResultSet resultSet, int i) throws SQLException {
                RolePrivilege rolePrivilege = new RolePrivilege();
                rolePrivilege.setId(resultSet.getInt("id"));
                rolePrivilege.setRoleId(resultSet.getInt("role_id"));
                rolePrivilege.setPrivilegeId(resultSet.getInt("privilege_id"));

                return rolePrivilege;
            }
        });
    }

    public List<RolePrivilege> assignPrivilege(Integer roleId, List<Integer> privilegeId){
        for(Integer id:privilegeId){
            String sql = "insert into relation_role_privilege values ( null, "+ roleId+", "+id+")";
            jdbcTemplate.update(sql);
        }
        return jdbcTemplate.query("select * from relation_role_privilege where role_id = " + roleId, new RowMapper<RolePrivilege>() {
            @Override
            public RolePrivilege mapRow(ResultSet resultSet, int i) throws SQLException {
                RolePrivilege rolePrivilege = new RolePrivilege();
                rolePrivilege.setId(resultSet.getInt("id"));
                rolePrivilege.setRoleId(resultSet.getInt("role_id"));
                rolePrivilege.setPrivilegeId(resultSet.getInt("privilege_id"));

                return rolePrivilege;
            }
        });
    }

}
