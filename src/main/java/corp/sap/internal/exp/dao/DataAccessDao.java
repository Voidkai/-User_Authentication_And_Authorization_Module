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
public class DataAccessDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Integer> getDataIdByUserId(Integer userId){
        String sql = "SELECT id FROM service_ticket WHERE user_id = "+userId;
        List<Integer> list = jdbcTemplate.query(sql, new IntegerRowMapper());

        return list;
    }
}

class IntegerRowMapper implements RowMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt("id");

    }
}
