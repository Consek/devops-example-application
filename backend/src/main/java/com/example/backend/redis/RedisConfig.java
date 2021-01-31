package com.example.backend.redis;

import com.example.backend.data.Instance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {


 @Bean
  public RedisTemplate<String, Instance> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Instance> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    return template;
  }

}
