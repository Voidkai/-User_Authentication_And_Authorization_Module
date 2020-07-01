package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.Data;
import corp.sap.internal.exp.domain.DataAccess;
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
    @Autowired
    DataDao dataDao;

    public List<DataAccess> getDataAccessByUserId(Integer userId, String dataName){
        Integer dataCode = dataDao.getCodeByName(dataName);
        String sql = "SELECT * FROM data_access WHERE uid = "+userId+" and "+"data_code = "+dataCode;
        List<DataAccess> dataAccessList = jdbcTemplate.query(sql, new DataAccessRowMapper());

        return dataAccessList;
    }

    public Integer addDataAccess(Integer userId, String dataName, Integer eid){
        Integer dataCode = dataDao.getCodeByName(dataName);
        String sql = "INSERT INTO data_access VALUE (null,"+dataCode+","+userId+","+eid+")";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select max(id) from data_access", Integer.class);
    }

    public Integer delDataAccess(Integer id){
        String sql = "DELETE FROM data_access WHERE id = "+id;
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select max(id) from data_access", Integer.class);
    }
}

class DataAccessRowMapper implements RowMapper<DataAccess> {
    @Override
    public DataAccess mapRow(ResultSet resultSet, int i) throws SQLException {
        return new DataAccess(resultSet.getInt("id"), resultSet.getInt("data_code"),resultSet.getInt("eid"),resultSet.getInt("uid"));
    }
}

class DataRowMapper implements RowMapper<Data>{

    @Override
    public Data mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Data(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getInt("code"));
    }
}
