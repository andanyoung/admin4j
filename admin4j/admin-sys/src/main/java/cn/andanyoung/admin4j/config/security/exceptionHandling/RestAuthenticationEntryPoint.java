package cn.andanyoung.admin4j.config.security.exceptionHandling;

import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import cn.andanyoung.admin4j.common.utils.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 */
@Log4j2
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(
          HttpServletRequest request,
          HttpServletResponse response,
          AuthenticationException authException)
          throws IOException, ServletException {

    log.warn("url: " + request.getRequestURL().toString() + authException.getLocalizedMessage());
    ResponseUtil.sendJSONResponse(
            ResponseEnum.AUTH_UNAUTHORIZED.setMessage(authException.getLocalizedMessage()));
  }
}
