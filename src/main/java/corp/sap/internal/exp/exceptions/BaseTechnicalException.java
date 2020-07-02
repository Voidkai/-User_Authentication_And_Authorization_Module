package corp.sap.internal.exp.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;

public class BaseTechnicalException extends Exception {

    private ProcessingStatusCode code;
    private String errorMsg;

    public BaseTechnicalException(String message) {
        this.code = ProcessingStatusCode.COMMON_FAIL;
        this.errorMsg = message;
    }

    public BaseTechnicalException(String message, ProcessingStatusCode code) {
        this.code = code;
        this.errorMsg = message;
    }

    public ProcessingStatusCode getCode() {
        return code;
    }

    public void setCode(ProcessingStatusCode code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
