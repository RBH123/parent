package ruan.gateway.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ruan.gateway.util.JwtUtils;

@Slf4j
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomerAuthenticProvider authenticProvider;

    static String[] NO_AUTHENTICATION = new String[]{
            "/**/login", "/**/register"
    };

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private NoPowerAccessHandler handler;
    @Autowired
    private CustomEntryPoint entryPoint;

    @Bean
    @SneakyThrows
    public AuthenticationManager manager() {
        return super.authenticationManager();
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
                .addFilterBefore(new LoginAuthentication(authenticProvider, jwtUtils),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticionFilter(),
                        UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl().disable();
        http.exceptionHandling()
                .accessDeniedHandler(handler)
                .authenticationEntryPoint(entryPoint);

    }

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
