package ruan.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ruan.gateway.entity.Users;
import ruan.gateway.service.UsersService;
import ruan.gateway.util.SnowflakesUtil;

@SpringBootTest
class GatewayApplicationTests {

    @Autowired
    private UsersService usersService;

    @Test
    void contextLoads() {
        usersService.saveOrUpdate(
                Users.builder().userId(SnowflakesUtil.INSTANCE().nextId()).username("sytest6")
                        .password("123").build());
    }

}
