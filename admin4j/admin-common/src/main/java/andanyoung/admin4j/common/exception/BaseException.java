package andanyoung.admin4j.common.exception;

import andanyoung.admin4j.common.response.IResponse;
import lombok.Getter;

@Getter
/**
 * 基础异常类
 * 定义好初始化构造函数,并生成IResponse
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    protected IResponse responseEnum;
    /**
     * 异常消息参数
     */
    protected Object[] args;

    public BaseException(IResponse responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.responseEnum =
                new IResponse() {
                    @Override
                    public int getCode() {
                        return code;
                    }

                    @Override
                    public String getMessage() {
                        return msg;
                    }
                };
    }

    public BaseException(IResponse responseEnum, String message) {
        super(message);
        this.responseEnum = responseEnum;
    }

    public BaseException(IResponse responseEnum, Object[] args, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IResponse responseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }
}
