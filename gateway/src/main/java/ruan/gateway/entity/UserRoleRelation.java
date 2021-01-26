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
@TableName("user_role_relation")
public class UserRoleRelation extends Model<UserRoleRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigInteger id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

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


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String ROLE_ID = "role_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
