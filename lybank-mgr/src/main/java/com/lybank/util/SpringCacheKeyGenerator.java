package com.lybank.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

@Component(value = "springCacheKeyGenerator") //标记为组件
public class SpringCacheKeyGenerator implements KeyGenerator {

    public final static String KEYGENERATOR = "springCacheKeyGenerator";
    private final static int NO_PARAM_KEY = 0;
    private String keyPrefix = "cashdf";// key前缀，用于区分不同项目的缓存，建议每个项目单独设置

    public static boolean isSimpleValueType(Class<?> clazz) {
        return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || URI.class == clazz
                || URL.class == clazz || Locale.class == clazz || Class.class == clazz);
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }

    @Override
    public Object generate(Object target, Method method, Object... params) {

        char sp = ':';
        StringBuilder strBuilder = new StringBuilder(30);
        strBuilder.append(keyPrefix);
        strBuilder.append(sp);
        // 类名
        strBuilder.append(target.getClass().getSimpleName());
        strBuilder.append(sp);
        // 方法名
        strBuilder.append(method.getName());
        strBuilder.append(sp);
        if (params.length > 0) {
            // 参数值
            for (Object object : params) {
                if (object != null && isSimpleValueType(object.getClass())) {
                    strBuilder.append(object);
                } else {
                    strBuilder.append(toJson(object).hashCode());
                }
            }
        } else {
            strBuilder.append(NO_PARAM_KEY);
        }
        return strBuilder.toString();
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
