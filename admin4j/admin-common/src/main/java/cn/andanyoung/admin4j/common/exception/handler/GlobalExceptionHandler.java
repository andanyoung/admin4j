package cn.andanyoung.admin4j.common.exception.handler;

import cn.andanyoung.admin4j.common.constant.enums.EnvEnum;
import cn.andanyoung.admin4j.common.exception.BaseException;
import cn.andanyoung.admin4j.common.exception.BizException;
import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import cn.andanyoung.admin4j.common.response.enums.ServletResponseEnum;
import cn.andanyoung.admin4j.common.response.impl.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

/**
 * 全局异常处理器
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @Value("${spring.profiles.active}")
  private String profile;

  /**
   * 业务异常
   *
   * @param e 异常
   * @return 异常结果
   */
  @ExceptionHandler(value = BizException.class)
  @ResponseBody
  public Response handleBusinessException(BaseException e) {
    log.error(e.getMessage(), e);

    return new Response(e.getResponseEnum().getCode(), e.getMessage());
  }

  /**
   * 自定义异常
   *
   * @param e 异常
   * @return 异常结果
   */
  @ExceptionHandler(value = BaseException.class)
  @ResponseBody
  public Response handleBaseException(BaseException e) {
    log.error(e.getMessage(), e);

    return new Response(e.getResponseEnum().getCode(), e.getMessage());
  }

  /**
   * Controller上一层相关异常
   *
   * @param e 异常
   * @return 异常结果
   */
  @ExceptionHandler({
          NoHandlerFoundException.class,
          HttpRequestMethodNotSupportedException.class,
          HttpMediaTypeNotSupportedException.class,
          MissingPathVariableException.class,
          MissingServletRequestParameterException.class,
          TypeMismatchException.class,
          HttpMessageNotReadableException.class,
          HttpMessageNotWritableException.class,
          HttpMediaTypeNotAcceptableException.class,
          ServletRequestBindingException.class,
          ConversionNotSupportedException.class,
          MissingServletRequestPartException.class,
          AsyncRequestTimeoutException.class,
          MultipartException.class
  })
  @ResponseBody
  public Response handleServletException(Exception e) {

    if (e instanceof NoHandlerFoundException) {
      String requestURL = ((NoHandlerFoundException) e).getRequestURL();
      if (!requestURL.equals("/favicon.ico")) {
        log.error(e.getMessage(), e);
      }
      return new Response(ResponseEnum.NOT_FOUND);
    }
    log.error(e.getMessage(), e);
    int code = ResponseEnum.INTERNAL_SERVER_ERROR.getCode();
    try {
      ServletResponseEnum servletExceptionEnum =
              ServletResponseEnum.valueOf(e.getClass().getSimpleName());
      code = servletExceptionEnum.getCode();
    } catch (IllegalArgumentException e1) {
      log.error(
              "class [{}] not defined in enum {}",
              e.getClass().getName(),
              ServletResponseEnum.class.getName());
    }

    if (isShowDetailError()) {
      // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
      return new Response(code, "服务器内部错误：" + System.currentTimeMillis());
    }

    return new Response(code, e.getMessage());
  }

  /**
   * 参数绑定异常
   *
   * @param e 异常
   * @return 异常结果
   */
  @ExceptionHandler(value = BindException.class)
  @ResponseBody
  public Response handleBindException(BindException e) {
    log.error("参数绑定校验异常", e);

    // return wrapperBindingResult(e.getBindingResult());

    return new Response(ResponseEnum.METHOD_ARG_NOT_VALID.getCode(), e.getMessage());
  }

  /**
   * 参数校验异常，将校验失败的所有异常组合成一条错误信息
   *
   * @param e 异常
   * @return 异常结果
   */
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseBody
  public Response handleValidException(MethodArgumentNotValidException e) {
    log.error("参数绑定校验异常", e);

    return new Response(ResponseEnum.METHOD_ARG_NOT_VALID.getCode(), e.getMessage());
  }

  /**
   * 未定义异常
   *
   * @param e 异常
   * @return 异常结果
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Response handleException(Exception e) {
    log.error(e.getMessage(), e);
    e.printStackTrace();
    if (isShowDetailError()) {
      // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
      int code = ResponseEnum.INTERNAL_SERVER_ERROR.getCode();
      return new Response(code, "服务器内部错误：" + System.currentTimeMillis());
    }

    return new Response(ResponseEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
  }

  Boolean isShowDetailError() {
    return EnvEnum.PROD.getValue().equals(profile);
  }
}
