package ruan.provider.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ruan.provider.anno.CustomReturn;
import ruan.provider.common.CommonResult;
import ruan.provider.constant.ReturnTypeEnum;

@Slf4j
@Component
@ControllerAdvice
public class GlobalResponseBody implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        CustomReturn annotation = methodParameter.getMethodAnnotation(CustomReturn.class);
        if (annotation == null) {
            return true;
        }
        if (ReturnTypeEnum.NORMAL.equals(annotation.value())) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof CommonResult) {
            CommonResult result = (CommonResult) o;
            return result.toJson();
        }
        return CommonResult.SUCCESS(o).toJson();
    }
}
