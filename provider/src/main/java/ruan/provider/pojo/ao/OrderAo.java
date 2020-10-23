package ruan.provider.pojo.ao;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAo {

    /**
     * 店铺id
     */
    @NonNull
    private Long storeId;

    /**
     * 订单商品信息
     */
    @NonNull
    private List<OrderInfoAo> orderInfoAoList;
}
