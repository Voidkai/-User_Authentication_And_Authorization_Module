package corp.sap.internal.exp.service.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.exceptions.BaseTechnicalException;

public class NoPrivilegeException extends BaseTechnicalException {

    public NoPrivilegeException(String message) {
        super(message, ProcessingStatusCode.NO_PERMISSION);
    }

}
