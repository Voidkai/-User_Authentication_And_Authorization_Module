package corp.sap.internal.exp.service;

import org.springframework.stereotype.Service;

@Service
public interface PrivilegeCheckService {

    Boolean check(PermissionChallenge permissionChallenge);

}
