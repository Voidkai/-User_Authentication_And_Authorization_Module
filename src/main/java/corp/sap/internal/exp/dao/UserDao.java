package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Role;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.domain.UserGroup;
import corp.sap.internal.exp.domain.relation.UserRole;
import corp.sap.internal.exp.domain.relation.UserUserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String sql = "insert into user values (null,'" + username + "', '" + passwordEncoder.encode(password) + "')";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select * from user where username = '"+username +"'", new UserRowMapper());
    }

    public void deleteUser(Integer userId){
        String sql = "delete from user where id = "+ userId;
        jdbcTemplate.update(sql);
    }

    public UserRole assignRole(Integer userId, Integer roleId){
        String sql = "insert into relation_user_role values (null,"+userId+","+roleId+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("selet * from relation_user_role where user_id = " + userId + " and " + "role_id = " +
                roleId, new RowMapper<UserRole>() {
            @Override
            public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
                UserRole userRole= new UserRole();
                userRole.setId(resultSet.getInt("id"));
                userRole.setUserId(resultSet.getInt("user_id"));
                userRole.setRoleId(resultSet.getInt("role_id"));
                return userRole;
            }
        });
    }

    public List<UserRole> assignRole(Integer userId, List<Integer> roleId){
        String sql;
        for (Integer id : roleId) {
            sql = "insert into relation_user_role values (null,"+ userId+","+ id+")";
            jdbcTemplate.update(sql);
        }
        return jdbcTemplate.query("selet * from relation_user_role where user_id = " + userId, new UserRoleRowMapper());
    }

    public List<UserRole> assignRole(List<Integer> userId, Integer roleId){
        String sql;
        for (Integer id : userId) {
            sql = "insert into relation_user_role values (null,"+id+","+roleId+")";
            jdbcTemplate.update(sql);
        }
        return jdbcTemplate.query("selet * from relation_user_role where role_id = " + roleId, new UserRoleRowMapper());
    }

    public User getUserByID(Integer id){
        String sql = "select * from user where id =?";
        Object[] params = {id};
        User user = jdbcTemplate.queryForObject(sql, params, (resultSet, i) -> {
            User user1 = new User();
            user1.setId(resultSet.getInt("id"));
            user1.setUsername(resultSet.getString("username"));

            return user1;
        });
        return user;
    }

    public User findUserByName(String username){
        String sql = "select * from user where username = ?";
        Object[] params = {username};
        User user = jdbcTemplate.queryForObject(sql, params, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });

        return user;
    }

    public List<Role> getRoleByUserId(Integer user_id){
        String sql = "select * FROM relation_user_role WHERE user_id = "+ user_id;

        List<Role> list = jdbcTemplate.query(sql, new RoleRowMapper());

        return  list;
    }

    public List<User> getAllUsers() {
        String sql = "select * from user ";

        List<User> list = jdbcTemplate.query(sql, new UserRowMapper());

        return  list;
    }

    public User updatePassword(Integer userId, String password){
        String sql = "update user set password = '"+ password+"'where id = " + userId ;
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select * from user where id = '"+userId +"'", new UserRowMapper());
    }

    public User updateUsername(Integer userId, String username) {
        String sql = "update user set username = '"+ username+"'where id = " + userId ;
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select * from user where id = '"+userId +"'", new UserRowMapper());
    }


    public UserUserGroup joinUserGroup(Integer userId, Integer userGroupId){
        String sql = "insert into relation_user_user_group values (null,"+userId+" , "+userGroupId+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("selet * from relation_user_user_group  where user_id = " + userId, new RowMapper<UserUserGroup>() {
            @Override
            public UserUserGroup mapRow(ResultSet resultSet, int i) throws SQLException {
                UserUserGroup userUserGroup = new UserUserGroup();
                userUserGroup.setId(resultSet.getInt("id"));
                userUserGroup.setUserId(resultSet.getInt("user_id"));
                userUserGroup.setGroupId(resultSet.getInt("group_id"));
                return userUserGroup;
            }
        });
    }

}

class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        return role;
    }
}

class UserRoleRowMapper implements RowMapper<UserRole>{

    @Override
    public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
        UserRole userRole= new UserRole();
        userRole.setId(resultSet.getInt("id"));
        userRole.setUserId(resultSet.getInt("user_id"));
        userRole.setRoleId(resultSet.getInt("role_id"));
        return userRole;
    }
}

class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        return user;
    }
}
