package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.Impl.RBACPrivilegeChallenge;
import corp.sap.internal.exp.service.exceptions.NoPrivilegeException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketWithPrivilegeCheckService {

    @Autowired
    private ServiceTicketDao serviceTicketDao;
    @Autowired
    private DataAccessDao dataAccessDao;

    // inject by profile
    @Autowired
    private PrivilegeCheckService privilegeCheckService;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public ServiceTicket getTicketByTicketId(Integer userId, Integer id) throws NotSupportedException, NoPrivilegeException {
        RBACPrivilegeChallenge getAllTicketRBACPrivilege = new RBACPrivilegeChallenge(userId,"service_ticket_read");
        Boolean privilegeCheck = privilegeCheckService.check(getAllTicketRBACPrivilege);
        if (!privilegeCheck) throw new NoPrivilegeException("");
        return serviceTicketDao.getTicketByTicketId(id);
    }

    public ServiceTicket addTicket(Integer userId, String content) throws NotSupportedException, NoPrivilegeException {
        RBACPrivilegeChallenge getAllTicketRBACPermission = new RBACPrivilegeChallenge(userId,"service_ticket_create");
        Boolean privilegeCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!privilegeCheck) throw new NoPrivilegeException("");
        Integer id = serviceTicketDao.addTicket(userId, content);
        dataAccessDao.addDataAccess(userId,"service_ticket",id);
        return serviceTicketDao.getTicketByTicketId(id);

    }

    public ServiceTicket updateTicket(Integer id, Integer userId, String content) throws NotSupportedException, NoPrivilegeException {
        RBACPrivilegeChallenge getAllTicketRBACPermission = new RBACPrivilegeChallenge(userId,"service_ticket_update");
        Boolean privilegeCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!privilegeCheck) throw new NoPrivilegeException("");
        serviceTicketDao.updateTicket(id, userId, content);

        return serviceTicketDao.getTicketByTicketId(id);
    }

    public Integer delTicket(Integer id, Integer userId) throws NotSupportedException, NoPrivilegeException {
        RBACPrivilegeChallenge getAllTicketRBACPermission = new RBACPrivilegeChallenge(userId,"service_ticket_delete");
        Boolean privilegeCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!privilegeCheck) throw new NoPrivilegeException("");
        List<DataAccess> list= dataAccessDao.getDataAccessByUserId(userId, "service_ticket");
        for(DataAccess d : list){
            if(d.getEid() == id) dataAccessDao.delDataAccess(d.getId());
        }
        return serviceTicketDao.delTicket(id);
    }
}
