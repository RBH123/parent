package ruan.gateway.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import ruan.gateway.entity.UserInfo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author rocky
 */
@Slf4j
public class ObjectUtil {

    public static <T> T mapToObject(Map<String, Object> map, Class<T> cls) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cls);
            T t = cls.newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String name = descriptor.getName();
                if (StringUtils.isNoneBlank(name) && map.containsKey(name)) {
                    Object value = map.getOrDefault(name, "");
                    if (value != null) {
                        descriptor.getWriteMethod().invoke(t,value);
                    }
                }
            }
            return t;
        } catch (Exception e) {
            log.error("map转换bean异常:{}",e);
        }
        return null;
    }

    public static Map<String,Object> beanToMap(Object object){
        Map<String,Object> map = Maps.newHashMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if(descriptor.getName().equalsIgnoreCase("class")){
                    continue;
                }
                String name = descriptor.getName();
                Object value = descriptor.getReadMethod().invoke(object);
                if(value != null){
                    map.put(name,value);
                }
            }
        }catch (Exception e){
            log.error("bean转map异常:{}",e);
        }
        return map;
    }
}