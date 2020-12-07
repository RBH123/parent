package ruan.provider.util;

import com.google.common.collect.Maps;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import ruan.provider.anno.CustomMapping;

@Slf4j
public class ObjectUtil {

    @SneakyThrows
    public static XContentBuilder obj2XContentBuilder(Object obj, String pattern) {
        XContentBuilder builder =
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("dynamic_date_formats", pattern)
                        .startObject("properties");
        if (obj instanceof Map) {
            return null;
        }
        CustomMapping annotation = obj.getClass().getAnnotation(CustomMapping.class);
        if (annotation == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("serialVersionUID")) {
                continue;
            }
            builder.startObject(field.getName());
            CustomMapping mapping = field.getAnnotation(CustomMapping.class);
            String type = field.getType().getTypeName();
            type = type.substring(type.lastIndexOf(".") + 1, type.length());
            if (mapping != null) {
                if (StringUtils.isNotBlank(mapping.type())) {
                    type = mapping.type();
                }
                String analyzer = mapping.analyzer();
                if (StringUtils.isNotBlank(analyzer)) {
                    builder.field("analyzer", analyzer);
                    builder.field("search_analyzer", analyzer);
                }
            }
            type = convertBaseType2EsType(type);
            builder.field("type", type);
            builder.endObject();
        }
        builder.endObject().endObject();
        return builder;
    }

    public static String convertBaseType2EsType(String baseType) {
        String esType = null;
        if (StringUtils.isBlank(baseType)) {
            return esType;
        }
        baseType = baseType.toLowerCase();
        switch (baseType) {
            case "int":
                esType = "integer";
                break;
            case "biginteger":
                esType = "integer";
                break;
            case "string":
                esType = "keyword";
                break;
            case "localdate":
                esType = "date";
                break;
            case "localdatetime":
                esType = "date";
                break;
            case "decimal":
                esType = "double";
                break;
            case "bigdecimal":
                esType = "double";
                break;
            case "timestamp":
                esType = "date";
                break;
            default:
                esType = baseType;
                break;
        }
        return esType;
    }

    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            if (CollectionUtils.isEmpty(fieldList)) {
                return Collections.EMPTY_MAP;
            }
            Method[] declaredMethods = obj.getClass().getDeclaredMethods();
            List<Method> methodList = Arrays.asList(declaredMethods);
            if (CollectionUtils.isEmpty(methodList)) {
                return Collections.EMPTY_MAP;
            }
            methodList = methodList.parallelStream().filter(m -> m.getName().contains("get"))
                    .collect(Collectors.toList());
            List<String> methodNameList = methodList.stream().map(m -> m.getName())
                    .collect(Collectors.toList());
            for (Field f : fieldList) {
                if(f.getName().equalsIgnoreCase("serialVersionUID")){
                  continue;
                }
                f.setAccessible(true);
                int index = methodNameList.indexOf("get".concat(firstChar2Uper(f.getName())));
                Method method = methodList.get(index);
                Object invoke = method.invoke(obj);
                if(invoke == null || StringUtils.isBlank(invoke.toString())){
                  continue;
                }
                map.put(f.getName(), invoke);
            }
        } catch (Exception e) {
            log.error("转换异常！", e);
        }
        return map;
    }

    public static String firstChar2Uper(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        if (chars[0] <= 'z' && chars[0] >= 'a') {
            chars[0] -= 32;
        }
        return new String(chars);
    }
}
