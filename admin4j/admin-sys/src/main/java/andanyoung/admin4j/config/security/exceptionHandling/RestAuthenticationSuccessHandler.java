package andanyoung.admin4j.config.security.exceptionHandling;

import andanyoung.admin4j.common.response.enums.ResponseEnum;
import andanyoung.admin4j.common.response.impl.HttpResponse;
import andanyoung.admin4j.common.utils.IpUtil;
import andanyoung.admin4j.common.utils.ResponseUtil;
import andanyoung.admin4j.config.security.SysUserDetails;
import andanyoung.admin4j.config.security.jwt.JwtUtil;
import andanyoung.admin4j.dao.SysUserDao;
import andanyoung.admin4j.dao.SysUserLoginRecordDao;
import andanyoung.admin4j.entity.SysUser;
import andanyoung.admin4j.entity.SysUserLoginRecord;
import andanyoung.admin4j.enums.LoginTypeEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
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
@Configuration
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    SysUserDao sysUserDao;

    @Resource
    SysUserLoginRecordDao sysUserLoginRecordDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // 处理登入成功请求
        SysUserDetails userDetails = (SysUserDetails) authentication.getPrincipal();
        //重置 JwtSecret
        Integer uid = userDetails.getSysUser().getUid();
        SysUser sysUser = new SysUser();
        sysUser.setUid(uid);
        String secret = RandomStringUtils.randomAlphanumeric(6);
        sysUser.setJwtSecret(secret);
        sysUserDao.updateById(sysUser);

        String token = JwtUtil.sign(userDetails.getUsername(), secret);

        //插入登录成功日志
        SysUserLoginRecord sysUserLoginRecord = new SysUserLoginRecord();
        sysUserLoginRecord.setUid(sysUser.getUid());
        sysUserLoginRecord.setLoginType(LoginTypeEnum.USERNAME_PSD);
        sysUserLoginRecord.setIp(IpUtil.getIpAddrNumber(httpServletRequest));
        sysUserLoginRecord.setUserAgent(httpServletRequest.getHeader("User-Agent"));

        sysUserLoginRecordDao.insert(sysUserLoginRecord);

        HashMap<String, String> data = new HashMap<>(2);
        data.put("token", token);

        ResponseUtil.sendJSONResponse(new HttpResponse(ResponseEnum.SUCCESS, data, "登录成功"));
    }
}
