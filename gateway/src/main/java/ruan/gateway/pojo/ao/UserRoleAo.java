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
public class UserRoleAo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 角色主键
    */
    private BigInteger roleId;
    /**
    * 角色，0--普通用户，1--管理员
    */
    private Integer role;
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
