package ruan.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "es")
public class EsProperties {
    private String host;
    private Integer port;
    private String schema;
    private Integer connectTimeOut;
    private Integer socketTimeOut;
    private Integer connectRequestTimeOut;
    private Integer maxConnectNum;
    private Integer maxConnectPerRoute;
}
