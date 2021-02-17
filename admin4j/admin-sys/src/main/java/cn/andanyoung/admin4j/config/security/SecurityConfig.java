package cn.andanyoung.admin4j.config.security;

import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAccessDeniedHandler;
import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAuthenticationEntryPoint;
import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAuthenticationFailureHandler;
import cn.andanyoung.admin4j.config.security.exceptionHandling.RestAuthenticationSuccessHandler;
import cn.andanyoung.admin4j.config.security.jwt.filter.JwtAuthorizationFilter;
import cn.andanyoung.admin4j.config.security.jwt.filter.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/1 23:16
 */
@Configuration
@EnableConfigurationProperties(IgnoreUrlsConfig.class)
// 开启注解配置支持
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 自定义登录拦截器
     */
    @Bean
    JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter() throws Exception {
        JwtUsernamePasswordAuthenticationFilter authenticationFilter = new JwtUsernamePasswordAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
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


        http
                // 开启跨域
                .cors()
                .and()
                // security 默认 csrf 是开启的，我们使用了 token ，这个也没有什么必要了
                .csrf().disable()
                .authorizeRequests()
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
                .and()
                // 授权配置
                .authorizeRequests()
                // 所有请求
                .anyRequest()
                // 都需要认证;
                .authenticated()
                .and()
                .addFilterAt(jwtUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService));
    }
}
