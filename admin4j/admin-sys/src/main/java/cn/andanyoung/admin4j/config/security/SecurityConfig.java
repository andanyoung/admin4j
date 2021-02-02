package cn.andanyoung.admin4j.config.security;

import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAccessDeniedHandler;
import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAuthenticationEntryPoint;
import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAuthenticationFailureHandler;
import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/1 23:16
 */
@Configuration
@EnableConfigurationProperties(IgnoreUrlsConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        super.configure(auth);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.authorizeRequests();

        // 不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig.getUrls()) {
            registry.antMatchers(url).permitAll();
        }


        http.authorizeRequests()
                .and()
                .formLogin()
                .successHandler(new RestAuthenticationSuccessHandler())
                .failureHandler(new RestAuthenticationFailureHandler())
                .and()
                // 异常回调
                .exceptionHandling()
                // 未登录或登录过期
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                // 没有权限访问时
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .and()
                // 关闭 session 管理
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().and().csrf().disable()
                // 授权配置
                .authorizeRequests()
                // 所有请求
                .anyRequest().
                // 都需要认证;
                        authenticated();
    }
}
