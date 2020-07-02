package corp.sap.internal.exp.service.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.exceptions.BaseTechnicalException;

public class NotSupportedException extends BaseTechnicalException {
    public NotSupportedException(String message) {
        super(message, ProcessingStatusCode.PERMISSION_CHALLENGE_ERROR);
    }
}
