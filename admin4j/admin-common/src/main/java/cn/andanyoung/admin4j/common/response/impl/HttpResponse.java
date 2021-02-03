package cn.andanyoung.admin4j.common.response.impl;


import cn.andanyoung.admin4j.common.response.IResponse;
import cn.andanyoung.admin4j.common.response.enums.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 21:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HttpResponse extends Response {
    /**
     * 数据列表
     */
    protected Object data;

    /**
     * 根据code message 生成结果
     *
     * @param responseEnum
     */
    public HttpResponse(IResponse responseEnum) {
        super(responseEnum.getCode(), responseEnum.getMessage());
        this.data = null;
    }

    /**
     * 根据code message 生成结果
     *
     * @param responseEnum
     * @param data         data里面的数据
     */
    public HttpResponse(ResponseEnum responseEnum, Object data) {
        super(responseEnum.getCode(), responseEnum.getMessage());
        this.data = data;
    }

    public HttpResponse(ResponseEnum responseEnum, Object data, String message) {
     
        super(responseEnum.getCode(), StringUtils.isEmpty(message) ? responseEnum.getMessage() : message);
        this.data = data;
    }
}