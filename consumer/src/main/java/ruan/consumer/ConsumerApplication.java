package ruan.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import ruan.consumer.util.BeanUtil;

@MapperScan(basePackages = "ruan.consumer.mapper")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "ruan.consumer")
public class ConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
        BeanUtil.context = context;
    }

}
