package corp.sap.internal.exp.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class ResponseWrapper<T> implements Serializable {
    private Boolean success;
    private Integer errorCode;
    private String errorMsg;
    private T data;

    public ResponseWrapper() {
    }

    public ResponseWrapper(boolean success) {
        this.success = success;
        this.errorCode = success ? ProcessingStatusCode.SUCCESS.getCode() : ProcessingStatusCode.COMMON_FAIL.getCode();
        this.errorMsg = success ? ProcessingStatusCode.SUCCESS.getMessage() : ProcessingStatusCode.COMMON_FAIL.getMessage();
    }

    public ResponseWrapper(boolean success, ProcessingStatusCode resultEnum) {
        this.success = success;
        this.errorCode = success ? ProcessingStatusCode.SUCCESS.getCode() : (resultEnum == null ? ProcessingStatusCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.errorMsg = success ? ProcessingStatusCode.SUCCESS.getMessage() : (resultEnum == null ? ProcessingStatusCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
    }

    public ResponseWrapper(boolean success, T data) {
        this.success = success;
        this.errorCode = success ? ProcessingStatusCode.SUCCESS.getCode() : ProcessingStatusCode.COMMON_FAIL.getCode();
        this.errorMsg = success ? ProcessingStatusCode.SUCCESS.getMessage() : ProcessingStatusCode.COMMON_FAIL.getMessage();
        this.data = data;
    }

    public ResponseWrapper(boolean success, ProcessingStatusCode resultEnum, T data) {
        this.success = success;
        this.errorCode = success ? ProcessingStatusCode.SUCCESS.getCode() : (resultEnum == null ? ProcessingStatusCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.errorMsg = success ? ProcessingStatusCode.SUCCESS.getMessage() : (resultEnum == null ? ProcessingStatusCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static ResponseWrapper success() {
        return new ResponseWrapper(true);
    }

    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper(true, data);
    }

    public static ResponseWrapper fail() {
        return new ResponseWrapper(false);
    }

    public static ResponseWrapper fail(ProcessingStatusCode resultEnum) {
        return new ResponseWrapper(false, resultEnum);
    }

    public static ResponseWrapper fail(Throwable throwable) {
        ResponseWrapper rt = new ResponseWrapper(false, ProcessingStatusCode.COMMON_FAIL);
        rt.setErrorMsg(throwable.getMessage());
        rt.setErrorCode(500);
        return rt;
    }

}
