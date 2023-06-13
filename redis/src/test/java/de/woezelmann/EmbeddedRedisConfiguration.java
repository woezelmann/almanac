package de.woezelmann;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
public class EmbeddedRedisConfiguration {

    private RedisServer redisServer;

    public EmbeddedRedisConfiguration(@Value("${spring.redis.port}") int redisPort) {
        this.redisServer = new RedisServer(redisPort);
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
