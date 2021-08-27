package corp.sap.internal.exp.service.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.exceptions.BaseTechnicalException;

public class NoEntityAccessException extends BaseTechnicalException {

    public NoEntityAccessException(String message) {
        super(message, ProcessingStatusCode.DATA_ACCESS_ERROR);
    }
}
