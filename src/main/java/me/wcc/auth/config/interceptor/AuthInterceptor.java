package me.wcc.auth.config.interceptor;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenStore tokenStore;

    public AuthInterceptor(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (null == authorization || !authorization.startsWith("Bearer ")) {
            response.setStatus(401);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("<error>Unauthorized</error>");
            return false;
        }
        String token = authorization.split(" ")[1];
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
        if (null == oAuth2Authentication) {
            response.setStatus(401);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("<error>Access_token_expired</error>");
            return false;
        }
        return true;
    }
}
