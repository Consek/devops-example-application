package com.example.backend.redis;

import com.example.backend.data.Instance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {


 @Bean
  public RedisTemplate<String, Instance> redisTemplate() {
    RedisTemplate<String, Instance> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    return template;
  }

 @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConFactory
        = new JedisConnectionFactory();
    jedisConFactory.setHostName("redis");
    jedisConFactory.setPort(6379);
    return jedisConFactory;
  }
}
