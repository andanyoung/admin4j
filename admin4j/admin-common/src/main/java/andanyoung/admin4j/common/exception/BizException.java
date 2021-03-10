package andanyoung.admin4j.common.exception;

import andanyoung.admin4j.common.response.IResponse;
import andanyoung.admin4j.common.response.enums.ResponseEnum;

/**
 * 业务异常类
 * 根据自身业务来抛出异常
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BizException(IResponse responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BizException(
            IResponse responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }

    public BizException(ResponseEnum assessLimit, String message) {
        super(assessLimit, message);
    }
}
