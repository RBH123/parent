package ruan.provider.pojo.ao;

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
public class GoodsInfoAo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键id
    */
    private Long id;
    /**
    * 商品名称
    */
    private String name;
    /**
    * 商品描述
    */
    private String desc;
    /**
    * 颜色
    */
    private String color;
    /**
    * 尺寸
    */
    private String size;
    /**
    * 标签
    */
    private String tag;
    /**
    * 分享链接
    */
    private String shareUrl;
    /**
    * 图片
    */
    private String images;
    /**
    * 价格
    */
    private BigDecimal price;
    /**
    * 销量
    */
    private Long sales;
    /**
    * 是否推荐
    */
    private Integer isRecommended;
    /**
    * 保障
    */
    private String guarantee;
    /**
    * 飘带
    */
    private String streamer;
    /**
    * 创建时间
    */
    private Timestamp createTime;
    /**
    * 更新时间
    */
    private Timestamp updateTime;
    /**
    * 库存数量
    */
    private Long inventoryCount;
    /**
    * 状态
    */
    private Integer status;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESC = "desc";
    public static final String COLOR = "color";
    public static final String SIZE = "size";
    public static final String TAG = "tag";
    public static final String SHARE_URL = "share_url";
    public static final String IMAGES = "images";
    public static final String PRICE = "price";
    public static final String SALES = "sales";
    public static final String IS_RECOMMENDED = "is_recommended";
    public static final String GUARANTEE = "guarantee";
    public static final String STREAMER = "streamer";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";
    public static final String INVENTORY_COUNT = "inventory_count";
    public static final String STATUS = "status";
}
