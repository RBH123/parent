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
@TableName("role_permission_relation")
public class RolePermissionRelation extends Model<RolePermissionRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "relation_id", type = IdType.INPUT)
    private BigInteger relationId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;

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


    public static final String RELATION_ID = "relation_id";

    public static final String ROLE_ID = "role_id";

    public static final String PERMISSION_ID = "permission_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    @Override
    protected Serializable pkVal() {
        return this.relationId;
    }

}
