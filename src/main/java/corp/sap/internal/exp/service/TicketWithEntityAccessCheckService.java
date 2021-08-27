package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.EntityAccessDao;
import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.domain.Entity;
import corp.sap.internal.exp.domain.EntityAccess;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.Impl.RBACDataAccessChallenge;
import corp.sap.internal.exp.service.Impl.RBACEntityAccessChallenge;
import corp.sap.internal.exp.service.exceptions.NoDataAccessException;
import corp.sap.internal.exp.service.exceptions.NoEntityAccessException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketWithEntityAccessCheckService {

    @Autowired
    private ServiceTicketDao serviceTicketDao;
    @Autowired
    private EntityAccessDao entityAccessDao;

    @Autowired
    private EntityAccessCheckService entityAccessCheckService;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public ServiceTicket getTicketByTicketId(Integer userId, Integer id) throws NotSupportedException, NoDataAccessException{
        RBACEntityAccessChallenge rbacEntityAccessChallenge = new RBACEntityAccessChallenge("service_ticket",userId,id);
        Boolean entityAccessCheck = entityAccessCheckService.check(rbacEntityAccessChallenge);
        if(!entityAccessCheck) throw new NoDataAccessException("");
        return serviceTicketDao.getTicketByTicketId(id);
    }

    public ServiceTicket addTicket(Integer userId, String content){
        Integer id = serviceTicketDao.addTicket(userId, content);
        entityAccessDao.addEntityAccess(userId,"service_ticket",id);
        return serviceTicketDao.getTicketByTicketId(id);

    }

    public ServiceTicket updateTicket(Integer id, Integer userId, String content) throws NotSupportedException, NoEntityAccessException {
        RBACEntityAccessChallenge rbacEntityAccessChallenge = new RBACEntityAccessChallenge("service_ticket",userId,id);
        Boolean entityAccessCheck = entityAccessCheckService.check(rbacEntityAccessChallenge);
        if(!entityAccessCheck) throw new NoEntityAccessException("");
        serviceTicketDao.updateTicket(id, userId, content);

        return serviceTicketDao.getTicketByTicketId(id);
    }

    public Integer delTicket(Integer id, Integer userId) throws NotSupportedException, NoEntityAccessException {
        RBACEntityAccessChallenge rbacEntityAccessChallenge = new RBACEntityAccessChallenge("service_ticket",userId,id);
        Boolean entityAccessCheck = entityAccessCheckService.check(rbacEntityAccessChallenge);
        if(!entityAccessCheck) throw new NoEntityAccessException("");
        List<EntityAccess> list= entityAccessDao.getEntityAccessByUserId(userId, "service_ticket");
        for(EntityAccess e : list){
            if(e.getEntityId() == id) entityAccessDao.delEntityAccess(e.getId());
        }
        return serviceTicketDao.delTicket(id);
    }
}
