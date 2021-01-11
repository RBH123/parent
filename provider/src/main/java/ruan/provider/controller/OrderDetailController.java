package ruan.provider.controller;


import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.provider.anno.DynamicDataSource;
import ruan.provider.pojo.vo.OrderDetailVo;
import ruan.provider.service.OrderDetailService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @DynamicDataSource
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public void detail(){
        OrderDetailVo detail = orderDetailService.detail(BigInteger.valueOf(2));
        System.out.println(detail);
    }

}
