package ruan.gateway.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ruan.gateway.util.JwtUtils;

@Slf4j
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    static String[] NO_AUTHENTICATION = new String[]{
            "/login","/logout","/register"
    };

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    @SneakyThrows
    public AuthenticationManager manager(){
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @SneakyThrows
    public void configure(HttpSecurity http){
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(NO_AUTHENTICATION).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginAuthentication(manager(),jwtUtils), UsernamePasswordAuthenticationFilter.class);
    }
}
