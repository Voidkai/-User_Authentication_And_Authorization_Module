package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.service.Impl.RBACDataAccessChallenge;
import corp.sap.internal.exp.service.Impl.RBACPermissionChallenge;
import corp.sap.internal.exp.service.exceptions.NoDataAccessException;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import corp.sap.internal.exp.service.exceptions.ParamNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTicketService {

    @Autowired
    private ServiceTicketDao serviceTicketDao;
    @Autowired
    private DataAccessDao dataAccessDao;

    // inject by profile
    @Autowired
    private PrivilegeCheckService privilegeCheckService;

    @Autowired
    private DataAccessCheckService dataAccessCheckService;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public ServiceTicket getTicketByTicketId(Integer userId, Integer id) throws NotSupportedException, NoPermissionException, NoDataAccessException, ParamNotValidException {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_read");
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        RBACDataAccessChallenge rbacDataAccessChallenge = new RBACDataAccessChallenge("service_ticket",userId,id);
        Boolean dataAccessCheck = dataAccessCheckService.check(rbacDataAccessChallenge);
        if (!permissionCheck) throw new NoPermissionException("");
        else if(!dataAccessCheck) throw new NoDataAccessException("");
        ServiceTicket serviceTicket = serviceTicketDao.getTicketByTicketId(id);
        if(serviceTicket == null) throw new ParamNotValidException("");
        return serviceTicketDao.getTicketByTicketId(id);
    }

    public ServiceTicket addTicket(Integer userId, String content) throws NotSupportedException, NoPermissionException {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_create");
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        if (!permissionCheck) throw new NoPermissionException("");
        Integer id = serviceTicketDao.addTicket(userId, content);
        dataAccessDao.addDataAccess(userId,"service_ticket",id);
        return serviceTicketDao.getTicketByTicketId(id);

    }

    public ServiceTicket updateTicket(Integer id, Integer userId, String content) throws NotSupportedException, NoPermissionException, NoDataAccessException {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_update");
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        RBACDataAccessChallenge rbacDataAccessChallenge = new RBACDataAccessChallenge("service_ticket",userId,id);
        Boolean dataAccessCheck = dataAccessCheckService.check(rbacDataAccessChallenge);
        if (!permissionCheck) throw new NoPermissionException("");
        else if(!dataAccessCheck) throw new NoDataAccessException("");
        serviceTicketDao.updateTicket(id, userId, content);

        return serviceTicketDao.getTicketByTicketId(id);
    }

    public Integer delTicket(Integer id, Integer userId) throws NotSupportedException, NoPermissionException, NoDataAccessException {
        RBACPermissionChallenge getAllTicketRBACPermission = new RBACPermissionChallenge(userId,"service_ticket_delete");
        Boolean permissionCheck = privilegeCheckService.check(getAllTicketRBACPermission);
        RBACDataAccessChallenge rbacDataAccessChallenge = new RBACDataAccessChallenge("service_ticket",userId,id);
        Boolean dataAccessCheck = dataAccessCheckService.check(rbacDataAccessChallenge);
        if (!permissionCheck) throw new NoPermissionException("");
        else if(!dataAccessCheck) throw new NoDataAccessException("");
        List<DataAccess> list= dataAccessDao.getDataAccessByUserId(userId, "service_ticket");
        for(DataAccess d : list){
            if(d.getEid() == id) dataAccessDao.delDataAccess(d.getId());
        }
        return serviceTicketDao.delTicket(id);
    }
}
