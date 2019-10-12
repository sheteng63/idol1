package com.tengs.idol.core.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 自定义响应结构, 转换类
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Map转换为JSON字符串
     *
     * @param map 需要转换的Map
     * @return
     */
    public static <E, V> String map2JsonString(Map<E, V> map) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (map == null || map.isEmpty()){
                return EMPTY_JSON_STRING;
            }

            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * JSON字符串转换为Map
     *
     * @param strJsonString 需要转换的JSON字符串
     * @return
     */
    public static <E, V> Map<E, V> jsonString2Map(String strJsonString) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (StringUtils.isEmpty(strJsonString)){
                strJsonString = EMPTY_JSON_STRING;
            }

            return objectMapper.readValue(strJsonString, Map.class);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * JSON字符串转换为Object
     *
     * @param strJsonString 需要转换的JSON字符串
     * @param clazz
     * @return
     */
    public static <T> T jsonString2Object(String strJsonString, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (StringUtils.isEmpty(strJsonString)){
                strJsonString = EMPTY_JSON_STRING;
            }

            return objectMapper.readValue(strJsonString, clazz);
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * 实例化ObjectMapper
     *
     * @return
     */
    public static ObjectMapper instanceObjectMapper() {
        JsonFactory jf = new JsonFactory();
        jf.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        return new ObjectMapper(jf);
    }

    /**
     * 空JSON字符串
     */
    private final static String EMPTY_JSON_STRING = "{}";
}
