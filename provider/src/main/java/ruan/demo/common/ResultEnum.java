package ruan.demo.common;

public enum ResultEnum {

    SUCCESS(200, "请求成功"),
    NOT_FOUND(404, "请求未找到"),
    SERVER_ERROR(500, "服务器异常"),
    PARAM_ERROR(501, "请求参数异常");

    private Integer code;
    private String message;

    ResultEnum() {
    }

    ResultEnum(Integer code, String message) {
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
