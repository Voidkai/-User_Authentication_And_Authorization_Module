package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.Impl.Permission;
import corp.sap.internal.exp.service.Impl.PrivilegeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTicketService {
    @Autowired
    ServiceTicketDao serviceTicketDao;

    @Autowired
    PrivilegeServiceImpl privilegeServiceImpl;

    public List<ServiceTicket> getAllTicket() {
        return serviceTicketDao.getAllTicket();
    }

    public List<ServiceTicket> getTicketByUserID(Integer userId) {
        return serviceTicketDao.getTicketByUserId(userId);
    }

    public List<ServiceTicket> getTicketByTicketId(Integer id){ return serviceTicketDao.getTicketByTicketId(id);}
    public List<ServiceTicket> addTicket(Integer user_id, String content) {
        Permission getAllTicketPermission = new Permission("create_ticket");
        Boolean permissionCheck = privilegeServiceImpl.privilegeCheck(getAllTicketPermission, user_id);
        if(!permissionCheck) return null;
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
