package cn.andanyoung.admin4j.config.security.exceptionHandling;

import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import cn.andanyoung.admin4j.common.response.impl.HttpResponse;
import cn.andanyoung.admin4j.common.utils.ResponseUtil;
import cn.andanyoung.admin4j.config.security.SysUserDetails;
import cn.andanyoung.admin4j.config.security.jwt.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 认证成功时的回调
 *
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/3 1:11
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // 处理登入成功请求
        SysUserDetails userDetails = (SysUserDetails) authentication.getPrincipal();
        String token = JwtUtil.sign(userDetails.getUsername(), userDetails.getPassword());

        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);

        ResponseUtil.sendJSONResponse(new HttpResponse(ResponseEnum.SUCCESS, data, "登录成功"));
    }
}
