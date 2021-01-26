package cn.andanyoung.admin4j.common.constant.enums;

import lombok.AllArgsConstructor;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 20:57
 */
@AllArgsConstructor
/**
 * 环境变量
 */
public enum EnvEnum {
    // 本地开发环境
    DEV("dev"),
    // 正式环境
    PROD("PROD"),
    // 测试环境
    TEST("TEST");
    private final String env;

    public String getValue() {
        return env;
    }
}
