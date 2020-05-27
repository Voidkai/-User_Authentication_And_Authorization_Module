package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import sun.security.util.Password;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(int id){
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

    public User findByUsername(String username){
        String sql = "select * from users where username = ?";
        Object[] params = {username};
        User user = jdbcTemplate.queryForObject(sql, params, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));

                return user;
            }
        });

        return user;
    }
    public void addUser(String username,String password){
        String sql = "insert into users(user_id,username,password) values(null,?,?)";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1,username);
            preparedStatement.setString(2, passwordEncoder.encode(password));
        });
    }
}
