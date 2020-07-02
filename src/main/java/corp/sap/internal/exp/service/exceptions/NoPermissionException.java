package corp.sap.internal.exp.service.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.exceptions.BaseTechnicalException;

public class NoPermissionException extends BaseTechnicalException {

    public NoPermissionException(String message) {
        super(message, ProcessingStatusCode.NO_PERMISSION);
    }

}
