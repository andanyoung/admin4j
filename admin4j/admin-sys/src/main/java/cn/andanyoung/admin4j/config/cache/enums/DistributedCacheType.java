package cn.andanyoung.admin4j.config.cache.enums;

import lombok.Getter;

/**
 * @author andyoung
 */
@Getter
public enum DistributedCacheType{

    /**
     * 分布式缓存
     */
    DEFAULT(0),
    DEFAULT_60(60);

    /**
     * 缓存时间
     */
    private final int expires;

    DistributedCacheType(int expires) {
        this.expires = expires;
    }
}
