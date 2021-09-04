package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.service.PrivilegeChallenge;
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


    @Override
    public Boolean check(PrivilegeChallenge privilegeChallenge) {
        if (privilegeChallenge instanceof RBACPrivilegeChallenge) {
            RBACPrivilegeChallenge rbacPrivilegeChallenge = (RBACPrivilegeChallenge) privilegeChallenge;
            Integer userId = rbacPrivilegeChallenge.getUserId();
            String permCode = rbacPrivilegeChallenge.getPrivilegeCode();

            List<Privilege> privList = privilegeDao.getPrivByUserId(userId);
            List<String> codeList = new ArrayList<>();
            for (Privilege priv : privList) codeList.add(priv.getCode());

            for (String code : codeList) {
                if (code.equals(permCode)) {
                    return true;
                }
            }
        }

        return false;
    }
}
