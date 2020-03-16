package com.achan.exam.util.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Achan
 * @date 2020/3/9
 */
@Component
public class SimpleStringRedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void set(String key, Object object, Integer time) throws JsonProcessingException {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, objectMapper.writeValueAsString(object), time, TimeUnit.SECONDS);
    }

    public void set(String key, Object object) throws JsonProcessingException {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, objectMapper.writeValueAsString(object));
    }

    public <T> T get(String key, Class<T> type) {
        String value = redisTemplate.opsForValue().get(key);
        // 对于数字objectMapper会自动把null转换为0
        if (value == null) {
            return null;
        }
        return objectMapper.convertValue(value, type);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
