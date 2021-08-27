package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Data;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.domain.Entity;
import corp.sap.internal.exp.domain.EntityAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EntityAccessDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EntityDao entityDao;

    public List<EntityAccess> getEntityAccessByUserId(Integer userId, String entityCode){
        Integer dataCode = entityDao.getCodeByName(entityCode);
        String sql = "SELECT * FROM entity_access WHERE user_id = "+userId+" and "+"entity_code = "+entityCode;
        List<EntityAccess> entityAccessList = jdbcTemplate.query(sql, new EntityAccessRowMapper());

        return entityAccessList;
    }

    public Integer addEntityAccess(Integer userId, String entityName, Integer entityId){
        Integer entityCode = entityDao.getCodeByName(entityName);
        String sql = "INSERT INTO data_access VALUE (null,"+entityCode+","+userId+","+entityId+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select max(id) from entity_access", Integer.class);
    }

    public Integer delEntityAccess(Integer id){
        String sql = "DELETE FROM entity_access WHERE id = "+id;
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select max(id) from entity_access", Integer.class);
    }
}

class EntityAccessRowMapper implements RowMapper<EntityAccess> {
    @Override
    public EntityAccess mapRow(ResultSet resultSet, int i) throws SQLException {
        return new EntityAccess(resultSet.getInt("id"), resultSet.getInt("entity_code"),resultSet.getInt("entity_id"),resultSet.getInt("user_id"));
    }
}

class EntityRowMapper implements RowMapper<Entity>{

    @Override
    public Entity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Entity(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getInt("code"));
    }
}
