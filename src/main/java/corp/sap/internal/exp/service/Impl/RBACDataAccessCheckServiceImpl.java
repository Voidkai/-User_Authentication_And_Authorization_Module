package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.service.DataAccessChallenge;
import corp.sap.internal.exp.service.DataAccessCheckService;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


public class RBACDataAccessCheckServiceImpl implements DataAccessCheckService {
    @Autowired
    DataAccessDao dataAccessDao;

    @Override
    public Boolean check(DataAccessChallenge dataAccessChallenge) throws NotSupportedException {
        if(dataAccessChallenge instanceof RBACDataAccessChallenge){
            RBACDataAccessChallenge rbacDataAccessChallenge = (RBACDataAccessChallenge) dataAccessChallenge;
            List<Integer> dataIdList = dataAccessDao.getDataIdByUserId(rbacDataAccessChallenge.getUserId());
            if(dataIdList.contains(rbacDataAccessChallenge.getDataId())) return true;
            else return false;
        }else {
            throw new NotSupportedException();
        }
    }
}
