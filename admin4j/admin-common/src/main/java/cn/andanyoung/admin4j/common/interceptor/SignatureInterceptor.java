package cn.andanyoung.admin4j.common.interceptor;

import cn.andanyoung.admin4j.common.signature.SignatureUtil;
import cn.andanyoung.admin4j.common.signature.annotation.SignatureAccess;
import cn.andanyoung.admin4j.common.utils.SpringContextUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 签名拦截器
 */
@Component
@Order(1)
public class SignatureInterceptor implements HandlerInterceptor {

  final static String SIGNATURE_WHITE_LIST_BEN_KEY = "signature_white_list";

  AntPathMatcher antMatchers = new AntPathMatcher();
  // private final Map<String, String[]>  =new CopyOnWriteArraySet<>(256);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {

    String requestURI = request.getRequestURI();

    if (isInWhiteList(requestURI)) {
      return false;
    }

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    SignatureAccess annotation = handlerMethod.getMethod().getAnnotation(SignatureAccess.class);

    // 方法注解值为false
    if (annotation != null && !annotation.value()) {
      return false;
    }
    SignatureAccess classAnnotation = handlerMethod.getClass().getAnnotation(SignatureAccess.class);
    if ((annotation != null && annotation.value())
            || (classAnnotation != null && classAnnotation.value())) {
      // 需要认证签名
      return SignatureUtil.check(
              request, getAppSecretByAppId(request.getHeader(SignatureUtil.DEFAULT_APPID_FIELD)));
    }

    return false;
  }

  public String getAppSecretByAppId(String appid) {
    return appid;
  }

  Boolean isInWhiteList(String uri) {
    Object signatureWhiteListConfig = SpringContextUtil.getBean(SIGNATURE_WHITE_LIST_BEN_KEY);
    if (signatureWhiteListConfig != null) {

      List<String> signatureWhiteList = (List) signatureWhiteListConfig;
      for (String pattern : signatureWhiteList) {
        if (antMatchers.match(pattern, uri)) {
          return true;
        }
      }
    }
    return false;
  }
}
