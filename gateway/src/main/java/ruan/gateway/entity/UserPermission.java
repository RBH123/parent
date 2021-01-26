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
@TableName("user_permission")
public class UserPermission extends Model<UserPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "permission_id", type = IdType.INPUT)
    private BigInteger permissionId;

    /**
     * 权限值
     */
    @TableField("permission")
    private Integer permission;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 前端地址
     */
    @TableField("url")
    private String url;

    /**
     * 类型，0--目录，1--菜单，2--按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否删除
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


    public static final String PERMISSION_ID = "permission_id";

    public static final String PERMISSION = "permission";

    public static final String NAME = "name";

    public static final String DESC = "desc";

    public static final String URL = "url";

    public static final String TYPE = "type";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    @Override
    protected Serializable pkVal() {
        return this.permissionId;
    }

}
