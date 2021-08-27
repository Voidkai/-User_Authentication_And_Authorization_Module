package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.PermissionDao;
import corp.sap.internal.exp.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> getSelfPermission(Integer userId){
        List<Permission> permList = permissionDao.getPermByUserId(userId);
        return permList;
    }
}
