package ruan.provider.pojo.vo;

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
public class OrderInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
    * 店铺id
    */
    private Long storeId;
    /**
    * 商品id
    */
    private Long goodsId;
    /**
    * 商品数量
    */
    private Long goodsCount;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
    * 创建时间
    */
    private Timestamp createTime;
    /**
    * 更新时间
    */
    private Timestamp updateTime;

    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String GOODS_ID = "goods_id";
    public static final String GOODS_COUNT = "goods_count";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
}
