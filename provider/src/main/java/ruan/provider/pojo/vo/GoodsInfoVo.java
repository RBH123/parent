package ruan.provider.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import ruan.provider.anno.CustomMapping;

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
@CustomMapping
public class GoodsInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键id
    */
    private Long id;
    /**
    * 商品名称
    */
    @CustomMapping(type = "text",analyzer = "ik_smart")
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
}
