package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.domain.Role;
import corp.sap.internal.exp.service.PrivilegeChallenge;
import corp.sap.internal.exp.service.PrivilegeCheckService;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"rbac-basic"})
public class RBACPrivilegeCheckServiceImpl implements PrivilegeCheckService {

    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean check(PrivilegeChallenge privilegeChallenge) throws NotSupportedException {
        if (privilegeChallenge instanceof RBACPrivilegeChallenge) {
            RBACPrivilegeChallenge rbacPrivilegeChallenge = (RBACPrivilegeChallenge) privilegeChallenge;
            Integer userId = rbacPrivilegeChallenge.getUserId();
            String permCode = rbacPrivilegeChallenge.getPrivilegeCode();
            List<Role> roleList = userDao.getRoleByUserId(userId);

            List<Privilege> permIdList = new ArrayList<>();
            for (Role role : roleList) permIdList.addAll(privilegeDao.getPrivByRoleId(role.getId()));

            List<Privilege> permList = new ArrayList<>();
            for (Privilege privilege : permIdList)
                permList.addAll(privilegeDao.getPrivByPrivId(privilege.getId()));

            List<String> codeList = new ArrayList<>();
            for (Privilege perm : permList) codeList.add(perm.getCode());

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
