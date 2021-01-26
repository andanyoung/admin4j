package cn.andanyoung.admin4j.common.response;

/**
 * <pre>
 *  API返回接口
 * </pre>
 */
public interface IResponse {
    /**
     * 获取返回码
     *
     * @return 返回码
     */
    int getCode();

    /**
     * 获取返回信息
     *
     * @return 返回信息
     */
    String getMessage();
}
