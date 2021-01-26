package cn.andanyoung.admin4j.common.response.enums;

import cn.andanyoung.admin4j.common.exception.assertion.impl.BizAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 20:54
 * @github https://github.com/andanyoung/admin4j
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements BizAssert {
    SUCCESS(200, "SUCCESS"), // 成功
    FAIL(400, "fail"), // 失败
    FORBIDDEN(403, "permission denied"), // 权限不足
    EXCEPTION(501, "fail"), // 失败
    UNAUTHORIZED(401, "Unauthenticated (wrong signature)"), // 未认证（签名错误）
    NOT_FOUND(404, "404"), // 接口不存在
    SIGN_AUTH(405, "Interface signature failed seriously"), // 接口签名认真失败
    SIGN_AUTH_TIME(406, "Interface signature failed seriously timeout"), // 接口签名认真失败 时间对不上
    METHOD_ARG_NOT_VALID(407, "interface ARG error"), // 接口参数绑定认证失败
    METHOD_ARG_BIND_ERROR(407, "interface ARG Bind error"), // 接口参数绑定失败
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"), // 服务器内部错误
    ARG_ERROR(501, "参数错误"), // 服务器内部错误
    OTHER_SERVER_ERROR(503, "服务器内部错误:3"), // 第三方服务器接口请求失败
    ASSESS_LIMIT(504, "访问次数受限制") // 访问次数受限制
    ;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    public ResponseEnum setMessage(String message) {
        this.message = message;
        return this;
    }
}
