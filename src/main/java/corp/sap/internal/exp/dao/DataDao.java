package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer getCodeByName(String dataName){
        String sql = "SELECT * FROM data WHERE name = '" + dataName+"'";
        List<Data> list = jdbcTemplate.query(sql, new DataRowMapper());
        return list.get(0).getCode();
    }
}
