package corp.sap.internal.exp.service;

import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.stereotype.Service;

@Service
public interface PrivilegeCheckService {

    Boolean check(PrivilegeChallenge privilegeChallenge) throws NotSupportedException;

}
