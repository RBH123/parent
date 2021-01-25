package ruan.provider.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * Author: rocky Date: 2021/1/22 9:31 Project: parent1 Package: ruan.provider.common
 */
@ConditionalOnWebApplication
public class WebFilterConfig {

    @Bean
    public WebFilter webFilter(){
        return new WebFilter();
    }
}
