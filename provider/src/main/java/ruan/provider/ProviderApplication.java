package ruan.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import ruan.common.common.GlobalExceptionHandler;
import ruan.common.config.GlobalResponseBody;
import ruan.common.config.JsonConfig;

@EnableFeignClients
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"ruan.provider", "ruan.common"})
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
