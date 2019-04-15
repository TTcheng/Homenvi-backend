package me.wcc.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * 这个类表明了此应用是OAuth2 的资源服务器，此处主要指定了受资源服务器保护的资源链接
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午9:53
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/auth/**", "/test/**", "/openApi/**", "/homenvi/**", "/users/**")
                .and()
                .authorizeRequests().antMatchers("/test/**", "/openApi/**", "/auth/login").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER", "SUPER_USER", "ROLE_SUPER")
                .and().logout().permitAll()
                .and().formLogin().permitAll()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}