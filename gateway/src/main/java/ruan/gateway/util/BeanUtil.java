package ruan.gateway.util;

import org.springframework.boot.web.reactive.context.ConfigurableReactiveWebApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Author: rocky
 * Date: 2021/2/23 16:30
 * Project: parent1
 * Package: ruan.gateway.util
 */
public class BeanUtil {

    public static ConfigurableApplicationContext context;

    public static <T> T getBean(Class<T> cls){
        T bean = context.getBean(cls);
        return bean;
    }

    public static <T> T getBean(String beanName,Class<T> cls){
        T bean = context.getBean(beanName, cls);
        return bean;
    }
}
