package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PrivilegeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"rbac-join"})
public class RBACPrivilegeCheckServiceWithJoinImpl implements PrivilegeCheckService {
    @Autowired
    private PrivilegeDao privilegeDao;
    @Autowired
    private UserDao userDao;


    @Override
    public Boolean check(PermissionChallenge permissionChallenge) {
        if (permissionChallenge instanceof RBACPermissionChallenge) {
            RBACPermissionChallenge rbacPermissionChallenge = (RBACPermissionChallenge) permissionChallenge;
            Integer userId = rbacPermissionChallenge.getUserId();
            String privCode = rbacPermissionChallenge.getPrivilegeCode();

            List<Privilege> privList = privilegeDao.getPriByUserId(userId);
            List<String> codeList = new ArrayList<>();
            for (Privilege priv : privList) codeList.add(priv.getPrivilegeCode());

            for (String code : codeList) {
                if (code.equals(privCode)) {
                    return true;
                }
            }
        }

        return false;
    }
}
