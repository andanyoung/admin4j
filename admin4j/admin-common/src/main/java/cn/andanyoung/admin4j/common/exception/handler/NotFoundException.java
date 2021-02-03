package cn.andanyoung.admin4j.common.exception.handler;

import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import cn.andanyoung.admin4j.common.response.impl.Response;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 22:23
 */
@Controller
public class NotFoundException implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletRequest request) {

        return new Response(ResponseEnum.NoHandlerFoundException.getCode(), "api path not find");
    }
}
