package cn.andanyoung.admin4j.common.filter;

import cn.andanyoung.admin4j.common.utils.IpUtil;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/28 22:04
 */
@Log4j2
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        long endTime = System.currentTimeMillis();

        afterFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, endTime - startTime);
    }

    /**
     * 子类可以通过覆盖该方法，来自定义日志。
     * 又可以添加接口运行过长告警（比如接口运行时间/interval大于3s,就发生钉钉报警）
     * 还可以。。。自己玩
     *
     * @param request  HttpServletRequest
     * @param response ServletResponse
     * @param interval 请求的间隔时间
     * @throws UnknownHostException
     */
    protected void afterFilter(HttpServletRequest request, HttpServletResponse response, long interval) throws UnknownHostException {

        log.info(
                "IP:{} URL:{} METHOD:{} status:{} QueryString:{}  Referer:{} SPEND_TIME:{}ms",
                IpUtil.getIpAddr(request),
                request.getRequestURL(),
                request.getMethod(),
                response.getStatus(),
                request.getQueryString(),
                request.getHeader("Referer"),
                interval);
    }
}
