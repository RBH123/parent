package ruan.provider.pojo.vo;

import lombok.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* 库存流水明细
*
* @author ruan
* @since 2020-10-26
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDetailVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键id
    */
    private Long id;
    /**
    * 商品id
    */
    private Long goodsId;
    /**
    * 商品数量
    */
    private Long goodsCount;
    /**
    * 操作人id
    */
    private Long operatorPerson;
    /**
    * 创建时间
    */
    private Timestamp createTime;
    /**
    * 更新时间
    */
    private Timestamp updateTime;

    public static final String ID = "id";
    public static final String GOODS_ID = "goods_id";
    public static final String GOODS_COUNT = "goods_count";
    public static final String OPERATOR_PERSON = "operator_person";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
}
