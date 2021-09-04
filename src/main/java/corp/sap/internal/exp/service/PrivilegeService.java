package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.domain.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeDao privilegeDao;

    public List<Privilege> getSelfPermission(Integer userId){
        List<Privilege> permList = privilegeDao.getPrivByUserId(userId);
        return permList;
    }
}
