package ruan.provider.pojo.vo;

import lombok.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* 
*
* @author ruan
* @since 2020-10-26
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRecordVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键id
    */
    private Long id;
    /**
    * 消息信息
    */
    private String message;
    /**
    * 消息状态
    */
    private Integer status;
    /**
    * 消息类型，1--库存扣减，1--订单支付
    */
    private Integer messageType;
    /**
    * 创建时间
    */
    private Timestamp createTime;
    /**
    * 更新时间
    */
    private Timestamp updateTime;

    public static final String ID = "id";
    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String MESSAGE_TYPE = "message_type";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
}
