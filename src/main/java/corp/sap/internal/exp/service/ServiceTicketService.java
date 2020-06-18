package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.Impl.RBACPermissionChallenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTicketService {

    @Autowired
    private ServiceTicketDao serviceTicketDao;

    // inject by profile
    @Autowired
    private PrivilegeCheckService privilegeCheckService;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public List<ServiceTicket> getTicketByTicketId(Integer userId, Integer id) {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge("service_ticket_read");
        getAllTicketRBACPermission.setUserId(userId);
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) return null;
        return serviceTicketDao.getTicketByTicketId(id);
    }

    public List<ServiceTicket> addTicket(Integer userId, String content) {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge("service_ticket_create");
        getAllTicketRBACPermission.setUserId(userId);
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) return null;
        return serviceTicketDao.getTicketByTicketId(serviceTicketDao.addTicket(userId, content));

    }

    public List<ServiceTicket> updateTicket(Integer id, Integer userId, String content) {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge("service_ticket_update");
        getAllTicketRBACPermission.setUserId(userId);
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) return null;
        serviceTicketDao.updateTicket(id, userId, content);

        return serviceTicketDao.getTicketByTicketId(id);
    }

    public Integer delTicket(Integer id, Integer userId) {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge("service_ticket_delete");
        getAllTicketRBACPermission.setUserId(userId);
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) return null;
        return serviceTicketDao.delTicket(id);
    }
}
