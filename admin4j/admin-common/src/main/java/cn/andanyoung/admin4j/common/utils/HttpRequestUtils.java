package cn.andanyoung.admin4j.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName: HttpUtils @Description: http 工具类 获取请求中的参数
 */
public class HttpRequestUtils {

  /**
   * @param @param  request
   * @param @return
   * @param @throws IOException 参数
   * @return SortedMap<String, String> 返回类型
   * @throws
   * @Title: getAllParams @Description: 将URL的参数和body参数合并
   */
  public static SortedMap<String, String> getAllParams(
          HttpServletRequest request, TreeMap<String, String> otherParam) throws IOException {

    SortedMap<String, String> result = otherParam == null ? new TreeMap<>() : otherParam;
    // 获取URL上的参数
    Map<String, String> urlParams = getUrlParams(request);
    if (urlParams != null) {
      for (Map.Entry entry : urlParams.entrySet()) {
        result.put((String) entry.getKey(), (String) entry.getValue());
      }
    }

    Map<String, String> allRequestParam = new HashMap<>(16);
    // get请求不需要拿body参数
    if (!HttpMethod.GET.name().equals(request.getMethod())) {
      allRequestParam = getBodyParam(request);
    }
    // 将URL的参数和body参数进行合并
    if (allRequestParam != null) {
      for (Map.Entry entry : allRequestParam.entrySet()) {
        result.put((String) entry.getKey(), entry.getValue().toString());
      }
    }
    return result;
  }

  /**
   * @param @param  request
   * @param @return
   * @param @throws IOException 参数
   * @return Map<String, String> 返回类型``
   * @throws
   * @Title: getAllRequestParam @Description: 获取 Body 参数
   */
  public static Map getBodyParam(final HttpServletRequest request) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
    String str = "";
    StringBuilder wholeStr = new StringBuilder();
    // 一行一行的读取body体里面的内容；
    while ((str = reader.readLine()) != null) {
      wholeStr.append(str);
    }
    // 转化成json对象
    return JsonUtil.readMap(wholeStr.toString());
  }

  /**
   * @param @param  request
   * @param @return 参数
   * @return Map<String, String> 返回类型
   * @throws
   * @Title: getUrlParams @Description: 将URL请求参数转换成Map
   */
  public static Map<String, String> getUrlParams(HttpServletRequest request) {

    String param = "";

    try {
      String queryString = request.getQueryString();
      if (StringUtils.isNotEmpty(queryString)) {
        param = URLDecoder.decode(queryString, "utf-8");
      } else {
        return null;
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    Map<String, String> result = new HashMap<>(16);
    String[] params = param.split("&");

    for (String s : params) {
      int index = s.indexOf("=");
      result.put(s.substring(0, index), s.substring(index + 1));
    }
    return result;
  }
}
