package ruan.provider.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResult implements Serializable {

    private static final long serialVersionUID = -8851779914990589878L;
    private Integer code;
    private String message;
    private Object data;

    static CommonResult commonResult;

    public CommonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult() {
    }

    public static CommonResult SUCCESS() {
        commonResult = new CommonResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
        return commonResult;
    }

    public static CommonResult SUCCESS(Object data) {
        commonResult = new CommonResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
        return commonResult;
    }

    public static CommonResult FAIL() {
        commonResult = new CommonResult(ResultEnum.SERVER_ERROR.getCode(), ResultEnum.SERVER_ERROR.getMessage(), null);
        return commonResult;
    }

    public static CommonResult FAIL(Integer code, String message) {
        commonResult = new CommonResult(code, message, null);
        return commonResult;
    }

    public static CommonResult FAIL(String message) {
        commonResult = new CommonResult(ResultEnum.SERVER_ERROR.getCode(), message, null);
        return commonResult;
    }

    public String toJson() {
        return JSONObject.toJSONString(commonResult);
    }
}
