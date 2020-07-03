package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.Impl.RBACDataAccessChallenge;
import corp.sap.internal.exp.service.exceptions.NoDataAccessException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketWithDataAccessCheckService {

    @Autowired
    private ServiceTicketDao serviceTicketDao;
    @Autowired
    private DataAccessDao dataAccessDao;

    @Autowired
    private DataAccessCheckService dataAccessCheckService;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public ServiceTicket getTicketByTicketId(Integer userId, Integer id) throws NotSupportedException, NoDataAccessException{
        RBACDataAccessChallenge rbacDataAccessChallenge = new RBACDataAccessChallenge("service_ticket",userId,id);
        Boolean dataAccessCheck = dataAccessCheckService.check(rbacDataAccessChallenge);
        if(!dataAccessCheck) throw new NoDataAccessException("");
        return serviceTicketDao.getTicketByTicketId(id);
    }

    public ServiceTicket addTicket(Integer userId, String content){
        Integer id = serviceTicketDao.addTicket(userId, content);
        dataAccessDao.addDataAccess(userId,"service_ticket",id);
        return serviceTicketDao.getTicketByTicketId(id);

    }

    public ServiceTicket updateTicket(Integer id, Integer userId, String content) throws NotSupportedException, NoDataAccessException {
        RBACDataAccessChallenge rbacDataAccessChallenge = new RBACDataAccessChallenge("service_ticket",userId,id);
        Boolean dataAccessCheck = dataAccessCheckService.check(rbacDataAccessChallenge);
        if(!dataAccessCheck) throw new NoDataAccessException("");
        serviceTicketDao.updateTicket(id, userId, content);

        return serviceTicketDao.getTicketByTicketId(id);
    }

    public Integer delTicket(Integer id, Integer userId) throws NotSupportedException, NoDataAccessException {
        RBACDataAccessChallenge rbacDataAccessChallenge = new RBACDataAccessChallenge("service_ticket",userId,id);
        Boolean dataAccessCheck = dataAccessCheckService.check(rbacDataAccessChallenge);
        if(!dataAccessCheck) throw new NoDataAccessException("");
        List<DataAccess> list= dataAccessDao.getDataAccessByUserId(userId, "service_ticket");
        for(DataAccess d : list){
            if(d.getEid() == id) dataAccessDao.delDataAccess(d.getId());
        }
        return serviceTicketDao.delTicket(id);
    }
}
