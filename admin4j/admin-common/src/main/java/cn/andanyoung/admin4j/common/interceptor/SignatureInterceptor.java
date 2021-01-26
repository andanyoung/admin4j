package cn.andanyoung.admin4j.common.interceptor;

import cn.andanyoung.admin4j.common.signature.SignatureUtil;
import cn.andanyoung.admin4j.common.signature.annotation.SignatureAccess;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 签名拦截器
 */
@Component
@Order(1)
public class SignatureInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {

    // AntPathMatcher
    // 认证过的地址，需要签名，没有认证的（忽略）在这里也忽略
    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    SignatureAccess annotation = handlerMethod.getMethod().getAnnotation(SignatureAccess.class);

    // 方法注解值为false
    if (annotation != null && !annotation.value()) {
      return true;
    }
    SignatureAccess classAnnotation = handlerMethod.getClass().getAnnotation(SignatureAccess.class);
    if ((annotation != null && annotation.value())
            || (classAnnotation != null && classAnnotation.value())) {
      // 需要认证签名
      return SignatureUtil.check(
              request, getAppSecretByAppId(request.getHeader(SignatureUtil.DEFAULT_APPID_FIELD)));
    }

    return true;
  }

  public String getAppSecretByAppId(String appid) {
    return appid;
  }
}
