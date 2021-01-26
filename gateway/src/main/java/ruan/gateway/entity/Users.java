package ruan.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigInteger;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private BigInteger userId;

    /**
     * 用户名称
     */
    @TableField("username")
    private String username;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别，1--男，2--女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("mail")
    private String mail;

    /**
     * 角色
     */
    @TableField("role")
    private Integer role;

    /**
     * 是否删除，0--否，1--是
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Timestamp createdTime;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private Timestamp updatedTime;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
