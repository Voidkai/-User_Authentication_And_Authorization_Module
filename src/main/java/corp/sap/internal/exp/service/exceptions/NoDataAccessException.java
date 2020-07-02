package corp.sap.internal.exp.service.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.exceptions.BaseTechnicalException;

public class NoDataAccessException extends BaseTechnicalException {

    public NoDataAccessException(String message) {
        super(message, ProcessingStatusCode.DATA_ACCESS_ERROR);
    }
}
