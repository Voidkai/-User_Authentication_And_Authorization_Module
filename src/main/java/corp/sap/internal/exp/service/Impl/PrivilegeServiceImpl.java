package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    PrivilegeDao privilegeDao;

    @Override
    public Boolean privilegeCheck(Permission permission, Integer user_id) {
        String  privilegeCode= permission.getPrivilegeCode();

        List<String> privilegeCodeList = privilegeDao.getPrivCodeByPrivId(privilegeDao.getPrivIdByRoleId(privilegeDao.getRoleIdByUser(user_id)));
        for(String code : privilegeCodeList){
            if(code.equals(privilegeCode)) return true;
        }

        return false;
    }
}
