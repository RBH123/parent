package ruan.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.demo.common.CommonResult;
import ruan.demo.common.ResultEnum;
import ruan.demo.pojo.ao.OrderAo;
import ruan.demo.service.OrderInfoService;
import ruan.demo.service.ZookeeperDistributedLock;

import javax.validation.Valid;
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
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private ZookeeperDistributedLock zookeeperDistributedLock;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public Object createOrder(@RequestBody @Valid List<OrderAo> aos) {
        if (CollectionUtils.isEmpty(aos)) {
            return CommonResult.FAIL(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage()).toJson();
        }
        boolean orderResult = false;
        try {
            orderResult = orderInfoService.createOrder(aos);
        } catch (Exception e) {
            throw e;
        }
        if (orderResult) {
            return "订单创建成功！";
        }
        return "订单创建失败！";
    }
}
