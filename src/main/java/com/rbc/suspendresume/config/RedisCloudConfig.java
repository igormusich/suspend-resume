package com.rbc.suspendresume.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@Profile("cloud")
public class RedisCloudConfig extends AbstractCloudConfig {

    @Bean
    public RedisConnectionFactory redisConnection()
    {
        return connectionFactory().redisConnectionFactory();
    }
}