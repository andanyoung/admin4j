package cn.andanyoung.admin4j.config.cache;

import cn.andanyoung.admin4j.config.RedisConfig;
import cn.andanyoung.admin4j.config.cache.enums.LocalCacheType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author andanyoung
 */
@Configuration
@EnableCaching
@ConditionalOnProperty(prefix = "admin4j.cache",name = "distributed.enable",havingValue = "true")
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 自定义key生成器
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            // 采取拼接的方式生成key
            StringBuilder stringBuilder = new StringBuilder();
            // 目标类的类名
            stringBuilder.append(target.getClass().getName());
            stringBuilder.append(":");
            // 目标方法名
            stringBuilder.append(method.getName());
            // 参数
            for (Object object : params) {
                stringBuilder.append(":").append(object);
            }
            return stringBuilder;
        };
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存的默认过期时间，也是使用Duration设置
        config = config.entryTtl(Duration.ofMinutes(30L))
                // 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisConfig.jackson2JsonRedisSerializer()))
                // 不缓存空值
                .disableCachingNullValues();

        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames = new HashSet<>();
        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();

        for (LocalCacheType localCacheType : LocalCacheType.values()) {

            cacheNames.add(localCacheType.name());
            configMap.put(localCacheType.name(), config.entryTtl(Duration.ofMinutes(localCacheType.getExpires())));
        }

        // 使用自定义的缓存配置初始化一个cacheManager
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                // 一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .transactionAware()
                .build();
    }
}
