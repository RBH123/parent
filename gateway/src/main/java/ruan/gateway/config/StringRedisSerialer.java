package ruan.gateway.config;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Author: rocky
 * Date: 2021/2/24 11:52
 * Project: parent1
 * Package: ruan.gateway.config
 */
public class StringRedisSerialer<T> implements RedisSerializer<T> {

    private Charset format;
    private Class<T> cls;

    public StringRedisSerialer() {
        this.format = StandardCharsets.UTF_8;
    }

    public StringRedisSerialer(String format) {
        if (StringUtils.isEmpty(format)) {
            format = "UTF-8";
        }
        this.format = Charset.forName(format);
    }

    @Override
    @SneakyThrows
    public byte[] serialize(T t) {
        if (t == null) {
            return new byte[0];
        }
        return t.toString().getBytes(format);
    }

    @Override
    @SneakyThrows
    public T deserialize(byte[] bytes) {
        String str = new String(bytes, format);
        String string = JSONObject.toJSONString(str);
        T t = JSONObject.parseObject(string, cls);
        return t;
    }
}
