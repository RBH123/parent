package ruan.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperConfig {

    private String server;
    private Integer timeout;
}
