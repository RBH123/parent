package ruan.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigInteger;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色主键
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private BigInteger roleId;

    /**
     * 角色，0--普通用户，1--管理员
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


    public static final String ROLE_ID = "role_id";

    public static final String ROLE = "role";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
