package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.domain.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {
    @Autowired
    PrivilegeDao privilegeDao;


    public List<Privilege> getprivByUser(int id){
        return privilegeDao.getprivByUser(id);
    }

}
