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

    public List<ServiceTicket> getTicketByUserID(int id){
        return serviceTicketDao.getTicketByUserID(id);
    }

    public void addTicket(int user_id,String content){
        serviceTicketDao.addTicket(user_id,content);
    }

    public void updateTicket(int id,String content){ serviceTicketDao.updateTicket(id,content);}

    public void delTicket(int id){ serviceTicketDao.delTicket(id);}
}
