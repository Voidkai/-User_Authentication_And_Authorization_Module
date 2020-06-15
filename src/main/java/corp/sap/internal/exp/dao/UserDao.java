package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Role;
import corp.sap.internal.exp.domain.User;
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

    public User getUserByID(Integer id){
        String sql = "select * from users where user_id =?";
        Object[] params = {id};
        User user = jdbcTemplate.queryForObject(sql, params, (resultSet, i) -> {
            User user1 = new User();
            user1.setId(resultSet.getInt("user_id"));
            user1.setUsername(resultSet.getString("username"));

            return user1;
        });
        return user;
    }

    public User findUserByName(String username){
        String sql = "select * from users where username = ?";
        Object[] params = {username};
        User user = jdbcTemplate.queryForObject(sql, params, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });

        return user;
    }

    public List<Role> getRoleByUserId(Integer user_id){
        String sql = "select * FROM role_user WHERE user_id = "+ user_id;

        List<Role> list = jdbcTemplate.query(sql, new RoleRowMapper());

        return  list;
    }

}

class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(resultSet.getInt("role_id"));
        return role;
    }
}
