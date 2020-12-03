package ruan.provider.config;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(name = "org.springframework.web.servlet.DispatcherServlet")
public class MDCFilterAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(MDCFilter.class)
    public MDCFilter mdcFilter() {
        return new MDCFilter();
    }
}
