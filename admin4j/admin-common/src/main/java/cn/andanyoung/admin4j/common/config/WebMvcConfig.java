package cn.andanyoung.admin4j.common.config;

import cn.andanyoung.admin4j.common.interceptor.SignatureInterceptor;
import cn.andanyoung.admin4j.common.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/28 22:26
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${admin.api.sign.enable:true}")
    private Boolean adminApiSignEnable;
//    @Autowired
//    SignatureInterceptor signatureInterceptor;

    @Bean
    @ConditionalOnMissingBean(SignatureInterceptor.class)
    public SignatureInterceptor signatureInterceptor() {
        return new SignatureInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        if (adminApiSignEnable) {
            registry.addInterceptor(SpringContextUtil.getBean(SignatureInterceptor.class));
        }
    }
}
