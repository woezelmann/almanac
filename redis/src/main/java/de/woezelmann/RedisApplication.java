package de.woezelmann;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Bean
    public ReactiveRedisTemplate<String, Customer> customerReactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveRedisTemplate<>(
                connectionFactory,
                RedisSerializationContext
                        .<String, Customer>newSerializationContext(RedisSerializer.json())
                        .build()
        );
    }
}