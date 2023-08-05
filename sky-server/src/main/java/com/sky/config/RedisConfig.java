package com.sky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RedisConfig {
    @Bean // 作用：将方法的返回值放入到容器中，容器中这个组件默认的id就是方法名
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始配置redisTemplate");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        log.info("redisTemplate配置完成");
        return redisTemplate;
    }
}
