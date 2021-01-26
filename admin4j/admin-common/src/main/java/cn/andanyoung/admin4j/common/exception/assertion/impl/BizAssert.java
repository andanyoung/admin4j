package cn.andanyoung.admin4j.common.exception.assertion.impl;

import cn.andanyoung.admin4j.common.exception.BaseException;
import cn.andanyoung.admin4j.common.exception.BizException;
import cn.andanyoung.admin4j.common.exception.assertion.Assert;
import cn.andanyoung.admin4j.common.response.IResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * 业务异常断言
 */
public interface BizAssert extends IResponse, Assert {
    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(getMessage(), args);

        return new BizException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {

        String msg = StringUtils.isEmpty(t.getMessage()) ? getMessage() : t.getMessage();
        if (ArrayUtils.isNotEmpty(args)) {
            msg = MessageFormat.format(msg, args);
        }

        return new BizException(this, args, msg, t);
    }
}
