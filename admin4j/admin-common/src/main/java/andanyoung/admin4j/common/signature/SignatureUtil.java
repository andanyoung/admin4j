package andanyoung.admin4j.common.signature;

import andanyoung.admin4j.common.response.enums.ResponseEnum;
import andanyoung.admin4j.common.utils.BodyReaderHttpServletRequestWrapper;
import andanyoung.admin4j.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 接口签名工具
 */
@Slf4j
public class SignatureUtil {

  public static final int MAX_INTERNAL = 60000;
  public static final String DEFAULT_Authorization_FIELD = "Authorization";
  public static final String DEFAULT_APPID_FIELD = "appId";
  public static final String DEFAULT_TIMESTAMP_FIELD = "timestamp";
  public static final String DEFAULT_SIGN_FIELD = "sign";

  public static boolean check(ServletRequest req, String appSecret) throws IOException {

    HttpServletRequest request = (HttpServletRequest) req;
    // 防止流读取一次后就没有了, 所以需要将流继续写出去
    HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);

    String signField = requestWrapper.getHeader(DEFAULT_SIGN_FIELD);
    if (StringUtils.isEmpty(signField)) {

      ResponseUtil.sendJSONResponse(ResponseEnum.SIGN_AUTH_TIME);
      return false;
    }

    String timestamp = request.getHeader(DEFAULT_TIMESTAMP_FIELD);
    if (StringUtils.isEmpty(timestamp) || System.currentTimeMillis() - Long.parseLong(timestamp) > MAX_INTERNAL) {
      // 间隔时间 超过 MAX_INTERNAL////
      ResponseUtil.sendJSONResponse(ResponseEnum.SIGN_AUTH_TIME);
      return false;
    }


    Map<String, String[]> parameterMap = request.getParameterMap();
    parameterMap.put(DEFAULT_Authorization_FIELD, new String[]{request.getHeader(DEFAULT_Authorization_FIELD)});
    parameterMap.put(DEFAULT_APPID_FIELD, new String[]{request.getHeader(DEFAULT_APPID_FIELD)});
    parameterMap.put(DEFAULT_TIMESTAMP_FIELD, new String[]{timestamp});

    SortedMap<String, String[]> sortedMap = new TreeMap<>(parameterMap);

    return verifySign(sortedMap, signField, appSecret);
  }

  /**
   * @param params 所有的请求参数都会在这里进行排序加密
   * @return 验证签名结果
   */
  private static boolean verifySign(
          SortedMap<String, String[]> params, String urlSign, String appSecret) {

    // 把参数加密
    String paramsSign = calculateParamsSign(params, appSecret);
    return !StringUtils.isEmpty(paramsSign) && urlSign.equals(paramsSign);
  }

  /**
   * @param params 所有的请求参数都会在这里进行排序加密
   * @return 得到签名
   */
  private static String calculateParamsSign(SortedMap<String, String[]> params, String appSecret) {

    StringBuilder stringBuilder = new StringBuilder();

    Set<Map.Entry<String, String[]>> entry = params.entrySet();
    for (Map.Entry<String, String[]> temp : entry) {
      String[] values = temp.getValue();
      if (values.length > 1) {
        Arrays.sort(values);
      }
      for (String value : values) {
        stringBuilder.append(temp.getKey());
        stringBuilder.append("=");
        stringBuilder.append(value);
        stringBuilder.append("&");
      }
    }

    stringBuilder.append(appSecret);
    return DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes()).toUpperCase();
  }
}
