package ruan.gateway.security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ruan.gateway.service.TokenRecordService;
import ruan.gateway.util.JwtUtils;

@Slf4j
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    static String[] NO_AUTHENTICATION = new String[]{
            "/**/login", "/**/register", "/**/*.html", "/**/*.jsp"
    };

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private NoPowerAccessHandler handler;
    @Autowired
    private CustomEntryPoint entryPoint;
    @Autowired
    private CustomLogoutHandler logoutHandler;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private TokenRecordService tokenRecordService;

    @Bean
    @SneakyThrows
    public AuthenticationManager manager() {
        return new ProviderManager(new CustomerAuthenticProvider(userDetailsService));
    }

    @Bean
    public UserDetailsService detailsService() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @SneakyThrows
    public void configure(HttpSecurity http) {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(NO_AUTHENTICATION).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //登录验证过滤器
                .addFilterBefore(new LoginAuthenticationFilter(
                                new CustomerAuthenticProvider(userDetailsService), jwtUtils,tokenRecordService),
                        UsernamePasswordAuthenticationFilter.class)
                //权限校验过滤器
                .addFilterBefore(new JwtAuthenticionFilter(jwtUtils),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CustomLogoutFilter(logoutSuccessHandler, logoutHandler),
                        LogoutFilter.class);
        http.headers().cacheControl().disable();
        http.exceptionHandling()
                //没有权限返回信息
                .accessDeniedHandler(handler)
                //校验失败返回异常
                .authenticationEntryPoint(entryPoint);
    }

    @Bean
    public CustomerAuthenticProvider authenticProvider() {
        return new CustomerAuthenticProvider(userDetailsService);
    }

    @Override
    @SneakyThrows
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticProvider()).userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
