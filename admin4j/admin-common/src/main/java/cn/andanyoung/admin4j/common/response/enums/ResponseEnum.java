package cn.andanyoung.admin4j.common.response.enums;

import cn.andanyoung.admin4j.common.exception.assertion.impl.BizAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * 接口返回 code 定义
 *
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 20:54
 * @github https://github.com/andanyoung/admin4j
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements BizAssert {

    SUCCESS(10000, "SUCCESS"), // 成功


    //********** Servlet 错误 **********
    MethodArgumentNotValidException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    MethodArgumentTypeMismatchException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    MissingServletRequestPartException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    MissingPathVariableException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    BindException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    MissingServletRequestParameterException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    TypeMismatchException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    ServletRequestBindingException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    HttpMessageNotReadableException(40400, String.valueOf(HttpServletResponse.SC_BAD_REQUEST)),
    NoHandlerFoundException(40404, String.valueOf(HttpServletResponse.SC_NOT_FOUND)),
    NoSuchRequestHandlingMethodException(40404, String.valueOf(HttpServletResponse.SC_NOT_FOUND)),
    HttpRequestMethodNotSupportedException(40405, String.valueOf(HttpServletResponse.SC_METHOD_NOT_ALLOWED)),
    HttpMediaTypeNotAcceptableException(40406, String.valueOf(HttpServletResponse.SC_NOT_ACCEPTABLE)),
    HttpMediaTypeNotSupportedException(40415, String.valueOf(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE)),
    ConversionNotSupportedException(40500, String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)),
    HttpMessageNotWritableException(40500, String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)),
    AsyncRequestTimeoutException(40503, String.valueOf(HttpServletResponse.SC_SERVICE_UNAVAILABLE)),

    //********** 其他自定义 错误 **********
    BIZ_FAIL(50000, "biz fail"), // 业务异常失败

    INTERNAL_SERVER_ERROR(50501, "服务器内部错误"), // 服务器内部错误、
    OTHER_SERVER_ERROR(50502, "服务器内部错误:3"), // 第三方服务器接口请求失败

    Auth_USER_PWD_Fail(50440, "username or password fail"), // 用户名或者密码错误
    AUTH_FORBIDDEN(50403, "permission denied"), // 权限不足
    AUTH_UNAUTHORIZED(50401, "Unauthenticated"), // 未认证
    AUTH_TOKEN_ILLEGAL(50410, "Illegal token"),//非法 token token解析失败
    AUTH_TOKEN_INVALID(50411, "Invalid token"),//token 失效
    AUTH_TOKEN_EXPIRES(50412, "token Expires"),//token 过期


    SIGN_AUTH(50600, "Interface signature failed seriously"), // 接口签名认真失败

    SIGN_AUTH_TIME(50601, "Interface signature failed seriously timeout"), // 接口签名认真失败 时间对不上

    METHOD_ARG_NOT_VALID(50707, "interface ARG error"), // 接口参数绑定认证失败

    METHOD_ARG_BIND_ERROR(50708, "interface ARG Bind error"), // 接口参数绑定失败


    ASSESS_LIMIT(51000, "访问次数受限制") // 访问次数受限制

    ;

    /**
     * 返回码
     */
    private final int code;
    /**
     * 返回消息
     */
    private String message;

    public ResponseEnum setMessage(String message) {
        this.message = message;
        return this;
    }
}
