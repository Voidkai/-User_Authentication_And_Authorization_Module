package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.dao.ServiceTicketDao;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PrivilegeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RBACPrivilegeCheckServiceImpl implements PrivilegeCheckService {
    @Autowired
    private PrivilegeDao privilegeDao;


    @Override
    public Boolean check(PermissionChallenge permissionChallenge) {
        String privilegeCode= permissionChallenge.getPrivilegeCode();
        Integer userId = permissionChallenge.getUserId();

        List<String> privilegeCodeList = privilegeDao.getPrivCodeByPrivId(privilegeDao.getPrivIdByRoleId(privilegeDao.getRoleIdByUser(userId)));
        for(String code : privilegeCodeList){
            if(code.equals(privilegeCode)) return true;
        }

        return false;
    }
}
