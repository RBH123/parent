package ruan.consumer.pojo.ao;

import lombok.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* 
*
* @author ruan
* @since 2021-02-26
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersAo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 用户名称
    */
    private String username;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 性别，1--男，2--女
    */
    private Integer sex;
    /**
    * 手机号
    */
    private String mobile;
    /**
    * 邮箱
    */
    private String mail;
    /**
    * 角色
    */
    private Integer role;
    /**
    * 账户状态，0--正常，1--锁定，2--禁用
    */
    private Integer status;
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
