package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTicketService {
    @Autowired
    ServiceTicketDao serviceTicketDao;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public List<ServiceTicket> addTicket(Integer user_id, String content) {
        return serviceTicketDao.getTicketByTicketId(serviceTicketDao.addTicket(user_id, content));

    }

    public List<ServiceTicket> updateTicket(Integer id,Integer user_id, String content) {
        List<Integer> idList = serviceTicketDao.getTicketIdByUserId(user_id);
        if(!idList.contains(id)){
            return new ArrayList<>();
        }else{
            serviceTicketDao.updateTicket(id, user_id,content);
        }

        return serviceTicketDao.getTicketByTicketId(id);
    }

    public Integer delTicket(Integer id,Integer user_id) {
        List<Integer> idList = serviceTicketDao.getTicketIdByUserId(user_id);
        if(!idList.contains(id)){
            return 0;
        }
        return serviceTicketDao.delTicket(id,user_id);
    }
}
