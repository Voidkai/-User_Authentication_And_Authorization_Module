package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTicketService {
    @Autowired
    ServiceTicketDao serviceTicketDao;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(int id) {
        return serviceTicketDao.getTicketByUserID(id);
    }

    public int addTicket(int user_id, String content) {
        return serviceTicketDao.addTicket(user_id, content);
    }

    public int updateTicket(int id, String content) {
        return serviceTicketDao.updateTicket(id, content);
    }

    public int delTicket(int id) {
        return serviceTicketDao.delTicket(id);
    }
}
