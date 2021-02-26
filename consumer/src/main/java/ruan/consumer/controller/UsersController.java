package ruan.consumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ruan.consumer.service.UsersService;
import ruan.consumer.pojo.ao.UsersAo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ruan
 * @since 2021-02-26
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.POST,value = "/getUser")
    public Object getUsers(@RequestBody UsersAo ao){
        return usersService.getUsersVo(ao);
    }

}
