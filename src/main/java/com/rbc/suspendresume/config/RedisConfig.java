package com.rbc.suspendresume.config;



import com.rbc.suspendresume.domain.DataObject;
import com.rbc.suspendresume.repository.DataObjectRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public DataObjectRepositoryImpl repository(RedisTemplate<String, DataObject> redisTemplate) {
        return new DataObjectRepositoryImpl(redisTemplate);
    }

    @Bean
    public RedisTemplate<String, DataObject> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, DataObject> template = new RedisTemplate();

        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<DataObject> redisSerializer = new Jackson2JsonRedisSerializer<>(DataObject.class);

        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(redisSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(redisSerializer);

        return template;
    }
}