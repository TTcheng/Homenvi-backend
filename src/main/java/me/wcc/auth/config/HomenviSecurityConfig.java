package me.wcc.auth.config;

import me.wcc.auth.config.encoder.HomenviPasswordEncoder;
import me.wcc.auth.config.provider.HomenviUserAuthenticationProvider;
import me.wcc.auth.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HomenviSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailService customUserDetailService;
    @Autowired
    HomenviUserAuthenticationProvider authenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().csrf().disable();
        http.requestMatchers().antMatchers("/oauth/**", "/test/**", "/homenvi/**", "/openApi/**", "/auth/**")
                .and()
                .authorizeRequests()
                // 开放路由/oauth/**,/test/**
                .antMatchers("/oauth/**", "/test/**", "/auth/login").permitAll()
                // 其他URL均需授权
                .anyRequest().authenticated();
    }

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new HomenviPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
        auth.authenticationProvider(authenticationProvider);
        // auth.userDetailsService(homenviUserDetailService).passwordEncoder(bCryptEncoder());
        // auth.inMemoryAuthentication()
        // .withUser("user").password("123456").authorities("ROLE_USER");

    }

    /**
     * 需要配置这个支持password模式 support password grant type
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
