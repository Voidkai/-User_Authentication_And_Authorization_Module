package corp.sap.internal.exp.exceptions;

import corp.sap.internal.exp.dto.ProcessingStatusCode;

public class BaseBusinessException extends Exception {
    private Integer code;
    private String errorMsg;

    public BaseBusinessException(ProcessingStatusCode processingStatusCode){
        this.code = processingStatusCode.getCode();
        this.errorMsg = processingStatusCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
