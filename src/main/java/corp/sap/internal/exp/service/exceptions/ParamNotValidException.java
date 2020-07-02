package corp.sap.internal.exp.service.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.exceptions.BaseTechnicalException;

public class ParamNotValidException extends BaseTechnicalException {
    public ParamNotValidException(String message) {
        super(message, ProcessingStatusCode.PARAM_NOT_VALID);
    }
}
