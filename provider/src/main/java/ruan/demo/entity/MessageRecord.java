package ruan.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("message_record")
public class MessageRecord extends Model<MessageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 消息信息
     */
    @TableField("message")
    private String message;

    /**
     * 消息状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Timestamp updateTime;


    public static final String ID = "id";

    public static final String MESSAGE = "message";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
