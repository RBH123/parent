package ruan.gateway.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: rocky
 * Date: 2021/2/23 13:52
 * Project: parent1
 * Package: ruan.gateway.config
 */
@Configuration
@ConditionalOnClass(name = "org.springframework.web.servlet.DispatcherServlet")
public class MDCConfig {

    @Bean
    @ConditionalOnMissingBean(name = "MDCFilter")
    public MDCFilter filter(){
        return new MDCFilter();
    }
}
