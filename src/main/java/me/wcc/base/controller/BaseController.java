package me.wcc.base.controller;

import me.wcc.auth.domain.CustomUserDetails;
import me.wcc.auth.domain.entity.User;
import me.wcc.auth.service.CustomUserDetailService;
import me.wcc.base.exception.CommonException;
import me.wcc.base.infra.constant.BaseConstants;
import me.wcc.base.message.MessageAccessor;
import me.wcc.base.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author chuncheng.wang@hand-china.com 19-3-30 下午11:21
 */
public abstract class BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private CustomUserDetailService userDetailsService;
    @Autowired
    private CacheService cacheService;

    public HttpServletRequest getRequest() {
        return request;
    }

    protected void validateEmpty(String name, Object val) {
        if (StringUtils.isEmpty(val)) {
            String msg = MessageAccessor.getMessage(BaseConstants.ErrorCode.FIELD_NOT_NULL).desc();
            throw new CommonException(msg, name);
        }
    }

    protected HttpSession getSession() {
        return request.getSession();
    }

    /**
     * 获取当前登录用户
     *
     * @return 当前用户/null
     */
    protected User getCurrentUser() {
        String authorization = request.getHeader("Authorization");
        if (null == authorization || !authorization.startsWith("Bearer ")) {
            throw new CommonException("请先登录!");
        }
        String token = authorization.split(" ")[1];
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
        if (null == oAuth2Authentication) {
            return null;
        }
        Object details = oAuth2Authentication.getUserAuthentication().getDetails();
        if (details instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, String> detailsMap = (Map<String, String>) details;
            String username = detailsMap.get("username");
            /*
             * String grantType = detailsMap.get("grant_type"); String scope = detailsMap.get("scope"); String
             * clientId = detailsMap.get("client_id"); String clientSecret = detailsMap.get("client_secret");
             */
            UserDetails userDetails = cacheService.getCustomUserDetails(username);
            if (null == userDetails) {
                userDetails = userDetailsService.loadUserByUsername(username);
            }
            return ((CustomUserDetails) userDetails).getUser();
        }
        return null;
    }
}
