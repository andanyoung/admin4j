package andanyoung.admin4j.common.utils;

import andanyoung.admin4j.common.response.IResponse;
import andanyoung.admin4j.common.response.enums.ResponseEnum;
import andanyoung.admin4j.common.response.impl.HttpResponse;
import andanyoung.admin4j.common.response.impl.Response;
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

    public static Response fail(String message) {

        ResponseEnum fail = ResponseEnum.BIZ_FAIL;
        if (StringUtils.isNotEmpty(message)) fail.setMessage(message);
        return new HttpResponse(fail);
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
        out.write(responseStr);
        out.flush();
        out.close();
    }

    public static void sendJSONResponse(IResponse responseEnum) throws IOException {
        sendJSONResponse(responseEnum, null);
    }

    public static void sendJSONResponse(IResponse responseEnum, String message) throws IOException {

        if (StringUtils.isEmpty(message)) {
            sendJSONResponse(json(responseEnum));
        } else {
            sendJSONResponse(new Response(responseEnum.getCode(), message));
        }

    }
}
