package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Data;
import corp.sap.internal.exp.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer getCodeByName(String entityName){
        String sql = "SELECT * FROM entity WHERE name = '" + entityName+"'";
        List<Entity> list = jdbcTemplate.query(sql, new EntityRowMapper());
        return list.get(0).getCode();
    }

    public Integer getNameByCode (Integer entityCode){
        String sql = "SELECT * FROM entity WHERE code = '" + entityCode+"'";
        List<Entity> list = jdbcTemplate.query(sql, new EntityRowMapper());
        return list.get(0).getCode();
    }
}
