package me.wcc.auth.config.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.wcc.auth.domain.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * password模式下,用户名密码认证提供器
 *
 * @author chuncheng.wang@hand-china.com 19-3-28 下午5:45
 */
@Component
public class HomenviUserAuthenticationProvider implements AuthenticationProvider {
    // extends AbstractUserDetailsAuthenticationProvider
    private static final Logger LOGGER = LoggerFactory.getLogger(HomenviUserAuthenticationProvider.class);
    @Autowired
    @Qualifier("serializingObjectMapper")
    ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("homenviUserDetailService")
    UserDetailsService userDetailsService;

    /**
     * 认证逻辑
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String credentials = (String) authentication.getCredentials();

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(credentials, userDetails.getPassword())) {
            LOGGER.debug(">>>>>>>>>>>>>>BadCredentials<<<<<<<<<<<<<");
            throw new BadCredentialsException("BadCredentials");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String detailsString = null;
        try {
            detailsString = objectMapper.writeValueAsString(userDetails);
        } catch (JsonProcessingException e) {
            LOGGER.error("Authentication error", e);
        }
        return new UsernamePasswordAuthenticationToken(detailsString, credentials, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
