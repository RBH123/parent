package ruan.gateway.constant;

public enum GlobalEnum {
    ZERO(0, "否"),
    ONE(1, "是");

    private Integer code;
    private String Message;

    GlobalEnum(Integer code, String message) {
        this.code = code;
        Message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
