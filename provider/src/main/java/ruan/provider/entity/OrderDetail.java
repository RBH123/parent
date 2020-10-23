package ruan.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("order_detail")
public class OrderDetail extends Model<OrderDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 付款状态（0--未付款，1--已付款）
     */
    @TableField("pay_status")
    private Integer payStatus;

    /**
     * 物流状态（0--未发货，1--已发货）
     */
    @TableField("delivery_status")
    private Integer deliveryStatus;

    /**
     * 订单状态（0--未完成，1--已完成）
     */
    @TableField("order_status")
    private Integer orderStatus;

    /**
     * 订单总金额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 优惠金额
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    /**
     * 定金
     */
    @TableField("reserve_amount")
    private BigDecimal reserveAmount;

    /**
     * 商品总数量
     */
    @TableField("goods_count")
    private Long goodsCount;

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

    public static final String PAY_STATUS = "pay_status";

    public static final String DELIVERY_STATUS = "delivery_status";

    public static final String ORDER_STATUS = "order_status";

    public static final String TOTAL_AMOUNT = "total_amount";

    public static final String DISCOUNT_AMOUNT = "discount_amount";

    public static final String RESERVE_AMOUNT = "reserve_amount";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
