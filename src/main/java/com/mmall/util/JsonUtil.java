package com.mmall.util;

import com.mmall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Inclusion.ALWAYS);
        //mapper.setSerializationInclusion(Inclusion.NON_NULL);//序列化不是null的字段
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));//统一日期格式
        //反序列化
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return (obj instanceof String ? (String) obj : mapper.writeValueAsString(obj));
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("parse obj to string error", e);
            return null;

        }
    }

    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return (obj instanceof String ? (String) obj : mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj));
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("parse obj to string error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return ((clazz.equals(String.class) ? (T) str : mapper.readValue(str, clazz)));
        } catch (Exception e) {
            log.warn("parse string to obj error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectioClass, Class<?>... elementClasses) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectioClass, elementClasses);
        try {
            return mapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("parse string to obj error", e);
            return null;
        }
    }

    //序列化集合
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : mapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("parse string to obj error", e);
            return null;
        }
    }

    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("jihu");
        u1.setCreateTime(new Date());
        String s1 = JsonUtil.obj2String(u1);

        String s2 = JsonUtil.obj2StringPretty(u1);

        System.out.println(s1);
        System.out.println(s2);
        //user和u1的内存地址不一样
        User user = JsonUtil.string2Obj(s1, User.class);
    }
}
