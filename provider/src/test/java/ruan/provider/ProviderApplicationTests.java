package ruan.provider;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ruan.provider.service.ZookeeperDistributedLock;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ProviderApplicationTests {

    @Autowired
    ZookeeperDistributedLock zookeeperDistributedLock;

    @Test
    void contextLoads() {
    }
}
