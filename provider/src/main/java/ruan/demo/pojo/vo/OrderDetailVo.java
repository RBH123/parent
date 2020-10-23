package ruan.demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* 
*
* @author ruan
* @since 2020-10-20
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 付款状态（0--未付款，1--已付款）
    */
    private Integer payStatus;
    /**
    * 物流状态（0--未发货，1--已发货）
    */
    private Integer deliveryStatus;
    /**
    * 订单状态（0--未完成，1--已完成）
    */
    private Integer orderStatus;
    /**
    * 订单总金额
    */
    private BigDecimal totalAmount;
    /**
    * 优惠金额
    */
    private BigDecimal discountAmount;
    /**
    * 定金
    */
    private BigDecimal reserveAmount;
    /**
     * 商品总数量
     */
    private Long goodsCount;
    /**
    * 创建时间
    */
    private Timestamp createTime;
    /**
    * 更新时间
    */
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
}
