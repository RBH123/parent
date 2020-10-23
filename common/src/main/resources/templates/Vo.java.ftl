package ruan.demo.pojo.vo;

import lombok.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* ${table.comment!}
*
* @author ${author}
* @since ${date}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ${entity}Vo implements Serializable {
<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    /**
    * ${field.comment}
    */
    <#if (versionFieldName!"") == field.name>
        @Version
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>

<#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";
</#list>
}
