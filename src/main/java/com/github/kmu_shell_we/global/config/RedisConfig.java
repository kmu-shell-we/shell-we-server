package com.github.kmu_shell_we.global.config;

import com.github.kmu_shell_we.global.property.RedisProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperty  redisProperty;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(redisProperty.getHost(), redisProperty.getPort());
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {

        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }
}
