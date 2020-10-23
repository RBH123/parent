package ruan.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
@TableName("order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 订单id
     */
    @TableField("store_id")
    private Long storeId;

    /**
     * 商品id
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品数量
     */
    @TableField("goods_count")
    private Long goodsCount;

    /**
     * 商品价格
     */
    @TableField("price")
    private BigDecimal price;

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

    public static final String ORDER_ID = "order_id";

    public static final String GOODS_ID = "goods_id";

    public static final String GOODS_COUNT = "goods_count";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
