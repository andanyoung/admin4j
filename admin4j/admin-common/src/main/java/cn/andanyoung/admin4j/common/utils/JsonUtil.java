package cn.andanyoung.admin4j.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

  public static ObjectMapper getObjectMapper() {
    if (SpringContextUtil.getApplicationContext() == null) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper;
    }

    return (ObjectMapper) SpringContextUtil.getBean("jacksonObjectMapper");
  }

  /**
   * 解析json
   *
   * @param content
   * @param valueType
   * @return
   */
  public static <T> T readObject(String content, Class<T> valueType) {

    try {
      return getObjectMapper().readValue(content, valueType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <T> Map<String, T> readMap(String content) {

    try {
      return getObjectMapper().readValue(content, new TypeReference<Map<String, T>>() {
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  /**
   * @param json
   * @return java.util.List<java.util.Map < java.lang.String, ?>>
   * @Description json转List<Map < String, ?>>
   */
  public static List<Map<String, ?>> readMapList(String json) throws Exception {
    ObjectMapper objectMapper = getObjectMapper();
    JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Map.class);
    List<Map<String, ?>> mapList;
    try {
      mapList = (List<Map<String, ?>>) objectMapper.readValue(json, javaType);
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw e;
    } catch (JsonMappingException e) {
      e.printStackTrace();
      throw e;
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
    return mapList;
  }

  /**
   * 生成json
   *
   * @param object
   * @return
   */
  public static String toJson(Object object) {

    try {
      return getObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @param args
   * @return java.lang.String
   * @Description 数组转json
   */
  public static String arrayToJson(String[] args) {
    // 先讲数组转化为map，然后map转json
    Map<String, String> map = new HashMap<>();
    for (int i = 0; i < args.length; i++) {
      map.put(i + "", args[i]);
    }
    String json = JsonUtil.toJson(map);
    return json;
  }
}
