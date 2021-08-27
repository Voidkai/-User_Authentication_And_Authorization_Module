package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PermissionDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Permission;
import corp.sap.internal.exp.domain.Role;
import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PermissionCheckService;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"rbac-basic"})
public class RBACPermissionCheckServiceImpl implements PermissionCheckService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean check(PermissionChallenge permissionChallenge) throws NotSupportedException {
        if (permissionChallenge instanceof RBACPermissionChallenge) {
            RBACPermissionChallenge rbacPermissionChallenge = (RBACPermissionChallenge) permissionChallenge;
            Integer userId = rbacPermissionChallenge.getUserId();
            String permCode = rbacPermissionChallenge.getPrivilegeCode();
            List<Role> roleList = userDao.getRoleByUserId(userId);

            List<Permission> permIdList = new ArrayList<>();
            for (Role role : roleList) permIdList.addAll(permissionDao.getPermByRoleId(role.getId()));

            List<Permission> permList = new ArrayList<>();
            for (Permission permission : permIdList)
                permList.addAll(permissionDao.getPermByPrivId(permission.getId()));

            List<String> codeList = new ArrayList<>();
            for (Permission perm : permList) codeList.add(perm.getCode());

            for (String code : codeList) {
                if (code.equals(permCode)) {
                    return true;
                }
            }
        } else {
            throw new NotSupportedException("");
        }

        return false;
    }
}
