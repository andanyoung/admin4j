package cn.andanyoung.admin4j.config.security.exceptionHandling;

import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import cn.andanyoung.admin4j.common.response.impl.HttpResponse;
import cn.andanyoung.admin4j.common.utils.IpUtil;
import cn.andanyoung.admin4j.common.utils.ResponseUtil;
import cn.andanyoung.admin4j.common.utils.SpringContextUtil;
import cn.andanyoung.admin4j.config.security.SysUserDetails;
import cn.andanyoung.admin4j.config.security.jwt.JwtUtil;
import cn.andanyoung.admin4j.dao.SysUserLoginRecordDao;
import cn.andanyoung.admin4j.entity.SysUser;
import cn.andanyoung.admin4j.entity.SysUserLoginRecord;
import cn.andanyoung.admin4j.enums.LoginTypeEnum;
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

        //插入登录成功日志
        SysUserLoginRecord sysUserLoginRecord = new SysUserLoginRecord();
        SysUser sysUser = userDetails.getSysUser();
        sysUserLoginRecord.setUid(sysUser.getUid());
        sysUserLoginRecord.setLoginType(LoginTypeEnum.USERNAME_PSD);
        sysUserLoginRecord.setIp(IpUtil.getIpAddrNumber(httpServletRequest));
        sysUserLoginRecord.setUserAgent(httpServletRequest.getHeader("User-Agent"));

        SysUserLoginRecordDao sysUserLoginRecordDao = SpringContextUtil.getBean(SysUserLoginRecordDao.class);
        sysUserLoginRecordDao.insert(sysUserLoginRecord);


        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);

        ResponseUtil.sendJSONResponse(new HttpResponse(ResponseEnum.SUCCESS, data, "登录成功"));
    }
}
