package cn.andanyoung.admin4j.config.cache;

import cn.andanyoung.admin4j.config.cache.enums.DistributedCacheType;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author andanyoung
 */
@Configuration
@ConditionalOnProperty(prefix = "admin4j.cache",name = "local.enable",havingValue = "true",matchIfMissing = true)
public class CaffeineConfig {

    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caffeineCaches = new ArrayList<>();

        for (DistributedCacheType distributedCacheType : DistributedCacheType.values()) {
            caffeineCaches.add(new CaffeineCache(distributedCacheType.name(),
                    Caffeine.newBuilder()
                            .expireAfterWrite(distributedCacheType.getExpires(), TimeUnit.SECONDS)
                            .build()));
        }

        cacheManager.setCaches(caffeineCaches);

        return cacheManager;
    }
}
