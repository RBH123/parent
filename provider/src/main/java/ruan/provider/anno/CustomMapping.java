package ruan.provider.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(value = {ElementType.TYPE,ElementType.FIELD})
public @interface CustomMapping {
    String type() default "";
    String analyzer() default "";
}
