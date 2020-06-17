package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.domain.Role;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PrivilegeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("serviceNormal")
public class RBACPrivilegeCheckServiceImpl implements PrivilegeCheckService {
    @Autowired
    private PrivilegeDao privilegeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Boolean check(PermissionChallenge permissionChallenge) {
        if(permissionChallenge instanceof RBACPermissionChallenge){
           RBACPermissionChallenge rbacPermissionChallenge = (RBACPermissionChallenge) permissionChallenge;
           Integer userId = rbacPermissionChallenge.getUserId();
           String privCode = rbacPermissionChallenge.getPrivilegeCode();
              List<Role> roleList = userDao.getRoleByUserId(userId);

              List<Privilege> privIdList = new ArrayList<>();
              for(Role role:roleList) privIdList.addAll(privilegeDao.getPrivByRoleId(role.getRoleId()));

              List<Privilege> privList = new ArrayList<>();
              for(Privilege privilege:privIdList) privList.addAll(privilegeDao.getPrivByPrivId(privilege.getPrivilegeId()));

                List<String> codeList = new ArrayList<>();
                for(Privilege priv:privList) codeList.add(priv.getPrivilegeCode());

                for(String code : codeList){
                    if(code.equals(privCode)) {
                        return true;
                    }
                }
            }

        return false;
    }
}
