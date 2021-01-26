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
public class RolePermissionRelationAo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键id
    */
    private BigInteger relationId;
    /**
    * 角色id
    */
    private Long roleId;
    /**
    * 权限id
    */
    private Long permissionId;
    /**
    * 是否删除，0--否，1--是
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
