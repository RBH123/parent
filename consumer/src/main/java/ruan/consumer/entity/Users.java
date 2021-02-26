package ruan.consumer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

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
     * 账户状态，0--正常，1--锁定，2--禁用
     */
    @TableField("status")
    private Integer status;

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


    public static final String USER_ID = "user_id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String SEX = "sex";

    public static final String MOBILE = "mobile";

    public static final String MAIL = "mail";

    public static final String ROLE = "role";

    public static final String STATUS = "status";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
