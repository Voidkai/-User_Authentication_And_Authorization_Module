package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PrivilegeCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Profile({"rbac-basic-cache"})
public class RBACPrivilegeCheckServiceWithCacheImpl implements PrivilegeCheckService {

    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Boolean check(PermissionChallenge permissionChallenge) {
        if (permissionChallenge instanceof RBACPermissionChallenge) {
            RBACPermissionChallenge rbacPermissionChallenge = (RBACPermissionChallenge) permissionChallenge;
            Integer userId = rbacPermissionChallenge.getUserId();
            String privCode = rbacPermissionChallenge.getPrivilegeCode();
            String key = userId + "_" + privCode;
            ValueOperations<String, Boolean> operations = redisTemplate.opsForValue();
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                return operations.get(key);
            } else {

                List<Privilege> privList = privilegeDao.getPriByUserId(rbacPermissionChallenge.getUserId());
                List<String> codeList = new ArrayList<>();
                for (Privilege priv : privList) codeList.add(priv.getPrivilegeCode());

                for (String code : codeList) {
                    if (code.equals(rbacPermissionChallenge.getPrivilegeCode())) {
                        operations.set(key, true, 5, TimeUnit.HOURS);
                        return true;
                    } else {
                        operations.set(key, false, 5, TimeUnit.HOURS);
                    }
                }
            }
        }

        return false;
    }
}
