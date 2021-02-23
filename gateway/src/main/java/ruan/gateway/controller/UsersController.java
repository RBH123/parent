package ruan.gateway.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.gateway.service.UsersService;
import ruan.provider.pojo.ao.UsersAo;
import ruan.provider.pojo.vo.UsersVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ruan
 * @since 2021-01-25
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public Object getUser(@RequestBody UsersAo ao) {
        Object users = usersService.getUsersByUserId(ao);
        log.info("users:{}",users);
        return users;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody UsersAo usersAo) {
        Assert.notNull(usersAo, "请输入完成的注册信息！");
        UsersVo vo = new UsersVo();
        BeanUtils.copyProperties(usersAo, vo);
        boolean register = usersService.register(vo);
        return;
    }

}
