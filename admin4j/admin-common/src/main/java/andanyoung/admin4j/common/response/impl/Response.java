package andanyoung.admin4j.common.response.impl;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 21:39
 */

import andanyoung.admin4j.common.response.IResponse;
import andanyoung.admin4j.common.response.enums.ResponseEnum;
import lombok.Data;

/**
 * 基础返回结果
 */
@Data
public class Response implements IResponse {
    /**
     * 返回码
     */
    protected int code;
    /**
     * 返回消息
     */
    protected String message;

    public Response() {
        // 默认创建成功的回应
        this(ResponseEnum.SUCCESS);
    }

    public Response(IResponse responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMessage());
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}