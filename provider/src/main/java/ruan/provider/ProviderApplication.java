package ruan.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import ruan.provider.anno.EnableWebFilter;

//@EnableMethodCache(basePackages = "ruan.provider")
//@EnableCreateCacheAnnotation
@EnableWebFilter
@EnableRetry
@EnableFeignClients
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProviderApplication {

    public static void main(String[] args) {
//        ElasticApmAttacher.attach("classpath:/elasticapm.properties");
        SpringApplication.run(ProviderApplication.class, args);
    }
}
