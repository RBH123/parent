package ruan.provider.entity;

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
@TableName("goods_info")
public class GoodsInfo extends Model<GoodsInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 尺寸
     */
    @TableField("size")
    private String size;

    /**
     * 标签
     */
    @TableField("tag")
    private String tag;

    /**
     * 分享链接
     */
    @TableField("share_url")
    private String shareUrl;

    /**
     * 图片
     */
    @TableField("images")
    private String images;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 销量
     */
    @TableField("sales")
    private Long sales;

    /**
     * 是否推荐
     */
    @TableField("is_recommended")
    private Integer isRecommended;

    /**
     * 保障
     */
    @TableField("guarantee")
    private String guarantee;

    /**
     * 飘带
     */
    @TableField("streamer")
    private String streamer;

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

    /**
     * 库存数量
     */
    @TableField("inventory_cuantity")
    private Long inventoryCount;

    /**
     * 状态
     */
    @TableField("status")
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

    public static final String INVENTORY_QUANTITY = "inventory_quantity";

    public static final String STATUS = "status";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
