package ruan.provider.common;

public class ServerException extends Exception {

    private Integer code;

    public ServerException(String message) {
        super(message, null);
        this.code = ResultEnum.SERVER_ERROR.getCode();
    }

    public ServerException(Integer code, String message) {
        super(message, null);
        this.code = code;
    }
}
