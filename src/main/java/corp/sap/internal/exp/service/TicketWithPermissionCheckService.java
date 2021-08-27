package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.Impl.RBACPermissionChallenge;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketWithPermissionCheckService {

    @Autowired
    private ServiceTicketDao serviceTicketDao;
    @Autowired
    private DataAccessDao dataAccessDao;

    // inject by profile
    @Autowired
    private PermissionCheckService permissionCheckService;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public ServiceTicket getTicketByTicketId(Integer userId, Integer id) throws NotSupportedException, NoPermissionException{
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_read");
        Boolean permissionCheck = permissionCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) throw new NoPermissionException("");
        return serviceTicketDao.getTicketByTicketId(id);
    }

    public ServiceTicket addTicket(Integer userId, String content) throws NotSupportedException, NoPermissionException {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_create");
        Boolean permissionCheck = permissionCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) throw new NoPermissionException("");
        Integer id = serviceTicketDao.addTicket(userId, content);
        dataAccessDao.addDataAccess(userId,"service_ticket",id);
        return serviceTicketDao.getTicketByTicketId(id);

    }

    public ServiceTicket updateTicket(Integer id, Integer userId, String content) throws NotSupportedException, NoPermissionException {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_update");
        Boolean permissionCheck = permissionCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) throw new NoPermissionException("");
        serviceTicketDao.updateTicket(id, userId, content);

        return serviceTicketDao.getTicketByTicketId(id);
    }

    public Integer delTicket(Integer id, Integer userId) throws NotSupportedException, NoPermissionException{
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_delete");
        Boolean permissionCheck = permissionCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) throw new NoPermissionException("");
        List<DataAccess> list= dataAccessDao.getDataAccessByUserId(userId, "service_ticket");
        for(DataAccess d : list){
            if(d.getEid() == id) dataAccessDao.delDataAccess(d.getId());
        }
        return serviceTicketDao.delTicket(id);
    }
}
