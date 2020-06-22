package corp.sap.internal.exp.dto;

public enum ProcessingStatusCode {
    /* 成功 */
    SUCCESS(200, "Success"),

    /* 默认失败 */
    COMMON_FAIL(999, "Failure"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "PARAM_NOT_VALID"),
    PARAM_IS_BLANK(1002, "PARAM_IS_BLANK"),
    PARAM_TYPE_ERROR(1003, "PARAM_TYPE_ERROR"),
    PARAM_NOT_COMPLETE(1004, "PARAM_NOT_COMPLETE"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "USER_NOT_LOGIN"),
    USER_ACCOUNT_EXPIRED(2002, "USER_ACCOUNT_EXPIRED"),
    USER_CREDENTIALS_ERROR(2003, "USER_CREDENTIALS_ERROR"),
    USER_CREDENTIALS_EXPIRED(2004, "USER_CREDENTIALS_EXPIRED"),
    USER_ACCOUNT_DISABLE(2005, "USER_ACCOUNT_DISABLE"),
    USER_ACCOUNT_LOCKED(2006, "USER_ACCOUNT_LOCKED"),
    USER_ACCOUNT_NOT_EXIST(2007, "USER_ACCOUNT_NOT_EXIST"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "USER_ACCOUNT_ALREADY_EXIST"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "USER_ACCOUNT_USE_BY_OTHERS"),

    /* 业务错误 */
    NO_PERMISSION(3001, "NO_PERMISSION");
    private Integer code;
    private String message;

    ProcessingStatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        for (ProcessingStatusCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
