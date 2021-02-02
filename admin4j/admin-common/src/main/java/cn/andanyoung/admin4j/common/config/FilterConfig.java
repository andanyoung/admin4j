package cn.andanyoung.admin4j.common.config;

import cn.andanyoung.admin4j.common.filter.GlobalLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/28 22:15
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<GlobalLogFilter> globalFilter() {
        FilterRegistrationBean<GlobalLogFilter> filterRegistrationBean = new FilterRegistrationBean<GlobalLogFilter>();
        filterRegistrationBean.setFilter(new GlobalLogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);   //order的数值越小，在所有的filter中优先级越高
        return filterRegistrationBean;
    }
}
