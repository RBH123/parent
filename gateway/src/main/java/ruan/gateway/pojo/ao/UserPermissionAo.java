package ruan.provider.pojo.ao;

import java.math.BigInteger;
import lombok.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* 
*
* @author ruan
* @since 2021-01-25
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPermissionAo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键id
    */
    private BigInteger permissionId;
    /**
    * 权限值
    */
    private Integer permission;
    /**
    * 权限名称
    */
    private String name;
    /**
    * 描述
    */
    private String desc;
    /**
    * 前端地址
    */
    private String url;
    /**
    * 类型，0--目录，1--菜单，2--按钮
    */
    private Integer type;
    /**
    * 是否删除
    */
    private Integer isDeleted;
    /**
    * 创建时间
    */
    private Timestamp createdTime;
    /**
    * 更新时间
    */
    private Timestamp updatedTime;
}
