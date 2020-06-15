package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PrivilegeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RBACPrivilegeCheckServiceImpl implements PrivilegeCheckService {
    @Autowired
    private PrivilegeDao privilegeDao;


    @Override
    public Boolean check(PermissionChallenge permissionChallenge) {
        if(permissionChallenge instanceof RBACPermissionChallenge){
           RBACPermissionChallenge rbacPermissionChallenge = (RBACPermissionChallenge) permissionChallenge;
            List<String> privilegeCodeList = privilegeDao.getPrivCodeByPrivId(privilegeDao.getPrivIdByRoleId(privilegeDao.getRoleIdByUser(rbacPermissionChallenge.getUserId())));
            for(String code : privilegeCodeList){
                if(code.equals(rbacPermissionChallenge.getPrivilegeCode())) return true;
            }
        }

        return false;
    }
}
