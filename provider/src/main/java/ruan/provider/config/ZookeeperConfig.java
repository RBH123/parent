package ruan.provider.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.server}")
    private String server;
    @Value("${zookeeper.timeout}")
    private Integer timeout;

    private ZooKeeper zooKeeper;

    @Bean
    @SneakyThrows
    public ZooKeeper zooKeeper() {
        zooKeeper = new ZooKeeper(server, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info("zookeeper链接成功！");
            }
        });
        return zooKeeper;
    }
}
