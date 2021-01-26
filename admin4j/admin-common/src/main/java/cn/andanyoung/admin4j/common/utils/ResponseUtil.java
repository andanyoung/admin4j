package cn.andanyoung.admin4j.common.utils;

import cn.andanyoung.admin4j.common.response.IResponse;
import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import cn.andanyoung.admin4j.common.response.impl.HttpResponse;
import cn.andanyoung.admin4j.common.response.impl.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

    public static Response json(IResponse responseEnum) {
        return new HttpResponse(responseEnum);
    }

    public static Response success() {
        return success(null);
    }

    public static Response success(Object data) {
        return new HttpResponse(ResponseEnum.SUCCESS, data);
    }

    public static Response forbidden() {
        return new HttpResponse(ResponseEnum.FORBIDDEN);
    }

    public static Response unauthorized() {
        return new HttpResponse(ResponseEnum.UNAUTHORIZED);
    }

    public static Response fail(String message) {

        ResponseEnum fail = ResponseEnum.FAIL;
        if (StringUtils.isNotEmpty(message)) fail.setMessage(message);
        return new HttpResponse(fail);
    }

    public static Response fail() {

        return fail(null);
    }

    public static void sendJSONResponse(Response responseResult) throws IOException {

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse httpResponse = requestAttributes.getResponse();
        // 校验失败返回前端
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        String responseStr = JsonUtil.toJson(responseResult);
        PrintWriter out = httpResponse.getWriter();
        out.append(responseStr);
        out.flush();
        out.close();
    }

    public static void sendJSONResponse(IResponse responseEnum) throws IOException {

        sendJSONResponse(json(responseEnum));
    }
}
