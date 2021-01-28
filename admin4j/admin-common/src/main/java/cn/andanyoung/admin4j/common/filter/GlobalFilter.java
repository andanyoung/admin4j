package cn.andanyoung.admin4j.common.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/28 22:04
 */
@Log4j2
//@WebFilter(filterName = "globalFilter", urlPatterns = "/*")
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("start to doFilter");
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        //  log.info("the request of {} consumes {}ms.", getUrlFrom(request), (endTime - startTime));
        log.info("end to doFilter");
    }
}
