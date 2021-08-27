package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PermissionDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Permission;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"rbac-join"})
public class RBACPermissionCheckServiceWithJoinImpl implements PermissionCheckService {
    @Autowired
    private PermissionDao permissionDao;


    @Override
    public Boolean check(PermissionChallenge permissionChallenge) {
        if (permissionChallenge instanceof RBACPermissionChallenge) {
            RBACPermissionChallenge rbacPermissionChallenge = (RBACPermissionChallenge) permissionChallenge;
            Integer userId = rbacPermissionChallenge.getUserId();
            String permCode = rbacPermissionChallenge.getPrivilegeCode();

            List<Permission> permList = permissionDao.getPermByUserId(userId);
            List<String> codeList = new ArrayList<>();
            for (Permission perm : permList) codeList.add(perm.getCode());

            for (String code : codeList) {
                if (code.equals(permCode)) {
                    return true;
                }
            }
        }

        return false;
    }
}
