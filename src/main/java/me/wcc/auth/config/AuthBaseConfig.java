package me.wcc.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Order(0)
@Configuration
public class AuthBaseConfig {
    /**
     * 用户验证信息的保存策略
     */
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory connectionFactory) {
        // return new RedisTokenStore(connectionFactory);
        // return new InMemoryTokenStore();
        // return new JdbcTokenStore(dataSource);
        return new HomenviRedisTokenStore(connectionFactory);
    }
}
