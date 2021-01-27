package ruan.gateway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
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
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("token_record")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRecord extends Model<TokenRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "record_id", type = IdType.INPUT)
    private BigInteger recordId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private BigInteger userId;

    /**
     * token
     */
    @TableField("token")
    private String token;

    /**
     * token状态，0--正常，1--失效
     */
    @TableField("status")
    private Integer status;

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
        return this.recordId;
    }

}
