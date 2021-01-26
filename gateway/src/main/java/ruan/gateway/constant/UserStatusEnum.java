package ruan.gateway.constant;


public enum UserStatusEnum {
    NORMAL(0, "正常"),
    LOCK(1, "锁定"),
    FORBIDDEN(2, "禁用");

    private Integer code;
    private String message;

    UserStatusEnum(Integer code, String message) {
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
}
