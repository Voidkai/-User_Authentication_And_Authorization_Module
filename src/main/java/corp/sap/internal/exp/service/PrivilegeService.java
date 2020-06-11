package corp.sap.internal.exp.service;

import corp.sap.internal.exp.service.Impl.Permission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PrivilegeService {


//    List<Map<String,Object>> getprivByUser(Integer id);

    Boolean privilegeCheck(Permission permission, Integer user_id);

}
