package andanyoung.admin4j.config.cache.enums;

import lombok.Getter;

/**
 * 本地缓存
 *
 * @author andyoung
 */
@Getter
public enum LocalCacheType {

    /**
     * 本地缓存
     */
    LOCAL(0),
    LOCAL_60(60);

    /**
     * 缓存时间
     */
    private final int expires;

    LocalCacheType(int expires) {
        this.expires = expires;
    }
}
