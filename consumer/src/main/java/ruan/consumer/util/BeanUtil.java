package ruan.consumer.util;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * Author: rocky
 * Date: 2021/2/26 15:33
 * Project: parent1
 * Package: ruan.consumer.util
 */
public class BeanUtil {

    public static ConfigurableApplicationContext context;

    public static <T>T getBean(Class<T> tClass){
        T bean = context.getBean(tClass);
        return bean;
    }

    public static <T>T getBean(String className,Class<T> tClass){
        T bean = context.getBean(className, tClass);
        return bean;
    }
}
