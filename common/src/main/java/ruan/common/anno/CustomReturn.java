package ruan.common.anno;

import ruan.common.constant.ReturnTypeEnum;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(value = ElementType.METHOD)
public @interface CustomReturn {
    ReturnTypeEnum value() default ReturnTypeEnum.NORMAL;
}
