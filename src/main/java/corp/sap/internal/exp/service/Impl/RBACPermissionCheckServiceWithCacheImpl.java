package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.PermissionDao;
import corp.sap.internal.exp.domain.Permission;
import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.service.PermissionChallenge;
import corp.sap.internal.exp.service.PermissionCheckService;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
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
public class RBACPermissionCheckServiceWithCacheImpl implements PermissionCheckService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Boolean check(PermissionChallenge permissionChallenge) throws NotSupportedException {
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

                List<Permission> permList = permissionDao.getPermByUserId(rbacPermissionChallenge.getUserId());
                List<String> codeList = new ArrayList<>();
                for (Permission perm : permList) codeList.add(perm.getCode());

                for (String code : codeList) {
                    if (code.equals(rbacPermissionChallenge.getPrivilegeCode())) {
                        operations.set(key, true, 5, TimeUnit.HOURS);
                        return true;
                    } else {
                        operations.set(key, false, 5, TimeUnit.HOURS);
                    }
                }
            }
        } else {
            throw new NotSupportedException("");
        }

        return false;
    }
}
