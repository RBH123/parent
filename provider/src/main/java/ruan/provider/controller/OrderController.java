package ruan.provider.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.provider.common.CommonResult;
import ruan.provider.common.ResultEnum;
import ruan.provider.pojo.ao.OrderAo;
import ruan.provider.service.OrderService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
@Slf4j
@RestController
@RequestMapping("/orderInfo")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public Object createOrder(@RequestBody List<OrderAo> aos) {
        if (CollectionUtils.isEmpty(aos)) {
            return CommonResult.FAIL(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage()).toJson();
        }
        boolean orderResult = false;
        try {
            orderResult = orderService.createOrder(aos);
        } catch (Exception e) {
            throw e;
        }
        if (orderResult) {
            return "订单创建成功！";
        }
        return "订单创建失败！";
    }
}
