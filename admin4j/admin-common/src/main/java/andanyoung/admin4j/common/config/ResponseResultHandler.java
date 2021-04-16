package andanyoung.admin4j.common.config;

import andanyoung.admin4j.common.constant.annotations.GlobalResponseResult;
import andanyoung.admin4j.common.response.IResponse;
import andanyoung.admin4j.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * 将返回的json结果进行 包装
 *
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/3 22:34
 */
@Slf4j
@RestControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

  private static final Class<? extends Annotation> ANNOTATION_TYPE = GlobalResponseResult.class;

  @Value("${springfox.documentation.enabled}")
  private Boolean enableSwagger = true;

  @Override
  public boolean supports(
      MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

    GlobalResponseResult annotationMethod =
        (GlobalResponseResult) methodParameter.getContainingClass().getAnnotation(ANNOTATION_TYPE);
    GlobalResponseResult annotationClass =
        (GlobalResponseResult) aClass.getAnnotation(ANNOTATION_TYPE);
    if (annotationClass == null && annotationMethod == null) {
      // 没有注解默认不返回
      return true;
    } else if ((annotationMethod != null && annotationMethod.value())
        || (annotationClass != null && annotationClass.value())) {
      return true;
    }
    return false;
  }

  @Override
  public Object beforeBodyWrite(
      Object o,
      MethodParameter methodParameter,
      MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> aClass,
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {

    /** 这里需要排除 Swagger 的返回 */
    if (enableSwagger) {
      String packageName = o.getClass().getPackage().getName();
      String path = serverHttpRequest.getURI().getPath();
      if (StringUtils.startsWith(path, "/swagger-resources")) {
        return o;
      }
      if (StringUtils.startsWith(packageName, "springfox.")) {
        return o;
      }
    }

    if (o instanceof IResponse) {
      return o;
    } else {
      return ResponseUtil.success(o);
    }
  }
}
