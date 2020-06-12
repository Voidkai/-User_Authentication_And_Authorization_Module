package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ServiceTicketDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServiceTicket> getAllTicket() {
        String sql = "select * from service_ticket";
        return jdbcTemplate.query(sql, new TicketRowMapper());
    }
    public List<ServiceTicket> getTicketByTicketId(Integer Id) {
        String sql = "select * from service_ticket where id=" + Id;
        return jdbcTemplate.query(sql, new TicketRowMapper());
    }

    public List<ServiceTicket> getTicketByUserId(Integer userId) {
        String sql = "select * from service_ticket where user_id =" + userId;
        return jdbcTemplate.query(sql, new TicketRowMapper());
    }

    public Integer addTicket(Integer userId, String content) {
        String sql = "insert into service_ticket values (null,CURRENT_TIMESTAMP," + userId + ", '" + content + "')";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject("select max(id) from service_ticket", Integer.class);
    }

    public Integer updateTicket(Integer id, Integer userId, String content) {
        String sql = "update service_ticket set update_time=current_timestamp,content='" + content + "' where id = " + id +" and user_id ="+ userId;

        return jdbcTemplate.update(sql);
    }

    public Integer delTicket(Integer id) {
        String sql = "delete from service_ticket where id =" + id;

        return jdbcTemplate.update(sql);
    }

    public List<Integer> getTicketIdByUserId(Integer userId){
        String sql = "select id from service_ticket where user_id = "+userId;
        return jdbcTemplate.query(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });
    }

}

class TicketRowMapper implements RowMapper<ServiceTicket>{
    @Override
    public ServiceTicket mapRow(ResultSet resultSet, int i) throws SQLException {
        ServiceTicket serviceTicket = new ServiceTicket();
        serviceTicket.setId(resultSet.getInt("id"));
        serviceTicket.setUpdateTime(resultSet.getTimestamp("update_time"));
        serviceTicket.setUserId(resultSet.getInt("user_id"));
        serviceTicket.setContent(resultSet.getString("content"));
        return serviceTicket;
    }
}
