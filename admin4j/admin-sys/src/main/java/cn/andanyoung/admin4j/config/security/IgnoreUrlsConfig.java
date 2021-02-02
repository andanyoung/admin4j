package cn.andanyoung.admin4j.config.security;/**
 * @author andanyoung
 * @date 2021/2/3 0:48
 * @version 1.0
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/** 用于配置不需要保护的资源路径 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();
}
