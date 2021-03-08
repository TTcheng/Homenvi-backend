package me.wcc.auth.config;

import me.wcc.auth.exception.HomenviWebResponseExceptionTranslator;
import me.wcc.auth.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 授权服务配置
 */
@Component
public class AuthorizationConfigurer extends AuthorizationServerConfigurerAdapter implements WebMvcConfigurer {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Autowired
    private HomenviWebResponseExceptionTranslator webResponseExceptionTranslator;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .exceptionTranslator(webResponseExceptionTranslator)
                    .userDetailsService(userDetailsService)
                    .reuseRefreshTokens(false);
    }

    /**
     * 允许表单验证，浏览器直接发送post请求即可获取token
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                        .allowFormAuthenticationForClients();
    }
}
