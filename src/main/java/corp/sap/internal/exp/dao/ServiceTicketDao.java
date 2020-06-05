package corp.sap.internal.exp.dao;

import corp.sap.internal.exp.domain.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ServiceTicketDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ServiceTicket> getTicket(String sql) {
//        List rt = jdbcTemplate.queryForList(sql);
//        List<ServiceTicket> serviceTicketList = new ArrayList<>();
//        for (int i = 0; i < rt.size(); i++) {
//            Map<String, Object> serviceTicketMap = (Map<String, Object>) rt.get(i);
//            ServiceTicket serviceTicket = new ServiceTicket((Integer) serviceTicketMap.get("id"), (Timestamp) serviceTicketMap.get("update_time"), (Integer) serviceTicketMap.get("user_id"), (String) serviceTicketMap.get("content"));
//            serviceTicketList.add(serviceTicket);
//        }
        List<ServiceTicket> rt = jdbcTemplate.query(sql, (resultSet, i) -> {
            ServiceTicket serviceTicket = new ServiceTicket();
            serviceTicket.setId(resultSet.getInt("id"));
            serviceTicket.setUpdateTime(resultSet.getTimestamp("update_time"));
            serviceTicket.setUserId(resultSet.getInt("user_id"));
            serviceTicket.setContent(resultSet.getString("content"));
            return serviceTicket;
        });

        return rt;
    }

    public List<ServiceTicket> getAllTicket() {
        String sql = "select * from service_ticket";
        return getTicket(sql);
    }

    public List<ServiceTicket> getTicketByUserID(Integer id) {
        String sql = "select * from service_ticket where user_id =" + id;
        return getTicket(sql);
    }

    public Integer addTicket(Integer user_id, String content) {
        String sql = "insert into service_ticket values (null,CURRENT_TIMESTAMP," + user_id + ", '" + content + "')";
        return jdbcTemplate.update(sql);
    }

    public Integer updateTicket(Integer id, Integer user_id,String content) {
        String sql = "update service_ticket set update_time=current_timestamp,content='" + content + "' where id = " + id +", user_id ="+user_id;

        return jdbcTemplate.update(sql);
    }

    public Integer delTicket(Integer id) {
        String sql = "delete from service_ticket where id =" + id;

        return jdbcTemplate.update(sql);
    }

}
