package ruan.gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.gateway.common.CommonResult;
import ruan.gateway.service.UsersService;
import ruan.provider.pojo.ao.UsersAo;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public Object getUser(@RequestBody UsersAo ao) {
        Object users = usersService.getUsersByUserId(ao);
        return CommonResult.SUCCESS(users).toJson();
    }

}
