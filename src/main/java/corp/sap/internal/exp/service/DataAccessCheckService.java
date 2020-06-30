package corp.sap.internal.exp.service;

import corp.sap.internal.exp.service.exceptions.NotSupportedException;

public interface DataAccessCheckService {
    Boolean check(DataAccessChallenge dataAccessChallenge) throws NotSupportedException;
}
