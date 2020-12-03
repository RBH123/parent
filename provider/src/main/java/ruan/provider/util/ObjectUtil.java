package ruan.provider.util;

import java.lang.reflect.Field;
import java.util.Map;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import ruan.provider.anno.CustomMapping;

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
}
