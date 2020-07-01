package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.service.DataAccessChallenge;
import corp.sap.internal.exp.service.DataAccessCheckService;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RBACDataAccessCheckServiceImpl implements DataAccessCheckService {

    @Autowired
    DataAccessDao dataAccessDao;

    @Override
    public Boolean check(DataAccessChallenge dataAccessChallenge) throws NotSupportedException {
        if(dataAccessChallenge instanceof RBACDataAccessChallenge){
            RBACDataAccessChallenge rbacDataAccessChallenge = (RBACDataAccessChallenge) dataAccessChallenge;
            List<DataAccess> dataAccessList = dataAccessDao.getDataAccessByUserId(rbacDataAccessChallenge.getUid(), rbacDataAccessChallenge.getDataName());
            List<Integer> eidList = new ArrayList<>();
            for(DataAccess dataAccess : dataAccessList){
                eidList.add(dataAccess.getEid());
            }
            if(eidList.contains(rbacDataAccessChallenge.getEid())) return true;
            else return false;
        }else {
            throw new NotSupportedException();
        }
    }
}
