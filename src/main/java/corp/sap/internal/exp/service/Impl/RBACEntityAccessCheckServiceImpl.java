package corp.sap.internal.exp.service.Impl;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.EntityAccessDao;
import corp.sap.internal.exp.domain.DataAccess;
import corp.sap.internal.exp.domain.Entity;
import corp.sap.internal.exp.domain.EntityAccess;
import corp.sap.internal.exp.service.DataAccessChallenge;
import corp.sap.internal.exp.service.DataAccessCheckService;
import corp.sap.internal.exp.service.EntityAccessChallenge;
import corp.sap.internal.exp.service.EntityAccessCheckService;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RBACEntityAccessCheckServiceImpl implements EntityAccessCheckService {

    @Autowired
    EntityAccessDao entityAccessDao;

    @Override
    public Boolean check(EntityAccessChallenge entityAccessChallenge) throws NotSupportedException {
        if(entityAccessChallenge instanceof RBACEntityAccessChallenge){
            RBACEntityAccessChallenge rbacEntityAccessChallenge = (RBACEntityAccessChallenge) entityAccessChallenge;
            List<EntityAccess> entityAccessList = entityAccessDao.getEntityAccessByUserId(rbacEntityAccessChallenge.getUserId(), rbacEntityAccessChallenge.getEntityCode());
            List<Integer> entityIdList = new ArrayList<>();
            for(EntityAccess entityAccess : entityAccessList){
                entityIdList.add(entityAccess.getEntityId());
            }
            if(entityIdList.contains(rbacEntityAccessChallenge.getEntityId())) return true;
            else return false;
        }else {
            throw new NotSupportedException("");
        }
    }
}
