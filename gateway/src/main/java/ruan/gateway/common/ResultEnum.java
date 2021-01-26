package ruan.gateway.common;

public enum ResultEnum {

    SUCCESS(200, "请求成功"),
    NOT_FOUND(404, "请求未找到"),
    SERVER_ERROR(500, "服务器异常"),
    PARAM_ERROR(10001, "请求参数异常"),
    NO_POWERED(10002, "没有权限访问！"),
    TOKEN_OVERDUE(10003, "token已过期！"),
    PASSWORD_ERROR(10004, "密码错误！"),
    AUTHENTICATION_PARAM_ERROR(10005, "请输入完整的登录信息！"),
    ACCOUNT_LOCK(10006, "账户已锁定！"),
    ACCOUNT_FORBIDDEN(10007, "账户已禁用！"),
    ACCOUNT_NOT_FOUND(10008, "没有发现此账户，请先注册！");


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
