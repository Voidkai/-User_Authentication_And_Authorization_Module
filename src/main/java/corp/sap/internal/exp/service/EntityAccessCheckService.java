package corp.sap.internal.exp.service;

import corp.sap.internal.exp.service.exceptions.NotSupportedException;

public interface EntityAccessCheckService {
    Boolean check(EntityAccessChallenge entityAccessChallenge) throws NotSupportedException;
}
