package com.mmall.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Slf4j
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Inclusion.ALWAYS);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));//统一日期格式
        //反序列化
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return (obj instanceof String ? (String) obj : mapper.writeValueAsString(obj));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("parse obj to string error", e);
            return null;

        }
    }
}
