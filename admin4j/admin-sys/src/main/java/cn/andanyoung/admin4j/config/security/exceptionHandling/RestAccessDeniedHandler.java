package cn.andanyoung.admin4j.config.security.exceptionHandling;

import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import cn.andanyoung.admin4j.common.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：没有权限访问时
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
          HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e)
          throws IOException, ServletException {

    ResponseUtil.sendJSONResponse(ResponseEnum.AUTH_FORBIDDEN, e.getMessage());
  }
}
